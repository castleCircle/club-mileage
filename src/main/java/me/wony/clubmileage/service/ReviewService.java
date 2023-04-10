package me.wony.clubmileage.service;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toUnmodifiableList;
import static me.wony.clubmileage.dto.response.EventResponse.ofEmpty;
import static me.wony.clubmileage.entity.Point.createPointOfReview;
import static me.wony.clubmileage.type.EventActionType.ofCode;
import static org.springframework.util.StringUtils.hasText;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dao.PhotoRepository;
import me.wony.clubmileage.dao.PlaceRepository;
import me.wony.clubmileage.dao.PointRepository;
import me.wony.clubmileage.dao.ReviewRepository;
import me.wony.clubmileage.dao.UserRepository;
import me.wony.clubmileage.dto.request.EventRequest;
import me.wony.clubmileage.dto.response.EventResponse;
import me.wony.clubmileage.entity.Photo;
import me.wony.clubmileage.entity.Place;
import me.wony.clubmileage.entity.Point;
import me.wony.clubmileage.entity.Review;
import me.wony.clubmileage.entity.User;
import me.wony.clubmileage.exception.ResourceNotFoundException;
import me.wony.clubmileage.exception.ReviewValidationException;
import me.wony.clubmileage.type.EventActionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

  private final UserRepository userRepository;
  private final PlaceRepository placeRepository;
  private final PhotoRepository photoRepository;
  private final ReviewRepository reviewRepository;
  private final PointRepository pointRepository;


  private Map<EventActionType, Function<EventRequest, EventResponse>> eventActionHandler;


  @PostConstruct
  public void init(){
    eventActionHandler =
        Map.ofEntries(
            entry(EventActionType.ADD,this::create),
            entry(EventActionType.MOD,this::update),
            entry(EventActionType.DELETE,this::delete)
        );
  }

  private EventResponse exception(){
    throw new UnsupportedOperationException("요청 사항을 확인해주세요!");
  }

  @Transactional
  public EventResponse handle(final EventRequest dto){
    return eventActionHandler.getOrDefault(ofCode(dto.getAction()),(v)->exception()).apply(dto);
  }

  public EventResponse create(final EventRequest dto){

    final User user = userRepository.findById(dto.getUserId())
        .orElseThrow(
            () -> new ResourceNotFoundException("userId: " + dto.getUserId() + "cannot be found")
        );

    final Place place = placeRepository.findById(dto.getPlaceId())
        .orElseThrow(
            () -> new ResourceNotFoundException("placeId: " + dto.getPlaceId() + "cannot be found")
        );

    if(reviewRepository.existsDuplicateReviewAtPlace(dto.getUserId(),dto.getPlaceId())){
      throw new ReviewValidationException("같은 장소에 리뷰는 하나만 작성가능합니다!");
    }


    int point = calculatePoint(dto);

    final Review review = Review.builder()
        .id(dto.getReviewId())
        .user(user)
        .place(place)
        .content(dto.getContent())
        .build();


    for(UUID photoId : dto.getAttachedPhotoIds()){
      final Photo photo = photoRepository.findById(photoId)
          .orElseThrow(
              () -> new ResourceNotFoundException("photoId: " + photoId + "cannot be found")
          );
      review.addPhoto(photo);
    }

    reviewRepository.save(review);


    if(point > 0){
      final Point pointOfReview = createPointOfReview(user, review, point);
      pointRepository.save(pointOfReview);
    }

    return EventResponse.builder()
        .reviewId(review.getId())
        .build();
  }

  private int calculatePoint(final EventRequest dto){
    int point = 0;

    if(hasText(dto.getContent())){
      point++;
    }

    if(dto.getAttachedPhotoIds().size() > 0){
      point++;
    }

    if(isFirstReview(dto)){
      point++;
    }

    return point;
  }

  private boolean isFirstReview(final EventRequest dto){
    return !reviewRepository.existsReviewAtPlace(dto.getPlaceId());
  }

  public EventResponse update(final EventRequest dto){

    Review review = reviewRepository.findById(dto.getReviewId())
        .orElseThrow(() -> new ResourceNotFoundException("reviewId: " + dto.getReviewId() + "cannot be found"));

    boolean hasPhotos = review.hasAttachedPhoto();

    changePhoto(review,dto);

    int point = 0;

    if(hasOnlyContentBefore(review,hasPhotos,!dto.getAttachedPhotoIds().isEmpty())){
      point++;
    }

    if(isDeletePhotoAll(review,hasPhotos)){
      point--;
    }

    pointRepository.save(createPointOfReview(review.getUser(),review,point));

    review.changeContent(dto.getContent());

    return EventResponse.builder()
        .reviewId(review.getId())
        .build();
  }

  private void changePhoto(final Review review, final EventRequest dto){
    review.getAttachedPhotos().stream()
        .filter(photo -> !dto.getAttachedPhotoIds().contains(photo.getId()))
        .collect(toUnmodifiableList())
        .forEach(review::deletePhoto);

    for(final UUID photoIds : dto.getAttachedPhotoIds()){
      final Photo photo = photoRepository.findById(photoIds)
          .orElseThrow(
              () -> new ResourceNotFoundException("photoId: " + photoIds + "cannot be found"));
      review.addPhoto(photo);
    }
  }

  private boolean hasOnlyContentBefore(
      final Review review,
      final boolean hasPhotos,
      final boolean newPhotos){
    return !review.getContent().isEmpty() && !hasPhotos && newPhotos;
  }

  private boolean isDeletePhotoAll(
      final Review review,
      final boolean hasPhotos
      ){
    return !review.getContent().isEmpty() && hasPhotos && review.getAttachedPhotos().isEmpty();
  }


  public EventResponse delete(final EventRequest dto){

    Review review = reviewRepository.findById(dto.getReviewId())
        .orElseThrow(() -> new ResourceNotFoundException("reviewId: " + dto.getReviewId() + "cannot be found"));

    final Integer point = pointRepository.getPointByReviewId(review.getId());

    if(point > 0){
      pointRepository.save(createPointOfReview(review.getUser(),review,-point));
    }

    reviewRepository.deleteById(review.getId());

    return ofEmpty();
  }
}
