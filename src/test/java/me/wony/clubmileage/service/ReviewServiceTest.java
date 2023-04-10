package me.wony.clubmileage.service;

import static java.util.Set.of;
import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import me.wony.clubmileage.dao.PointRepository;
import me.wony.clubmileage.dto.request.EventRequest;
import me.wony.clubmileage.entity.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReviewServiceTest {

  @Autowired
  private PointRepository pointRepository;

  @Autowired
  private ReviewService reviewService;

  @Test
  @DisplayName("글1자 이상 & 사진 없고 & 특정 장소 첫리뷰인 경우 2점 얻어야 한다.")
  void shouldGet2PointWhenTextAndNewPlace(){

    final EventRequest eventAddRequest = EventRequest.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId(fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"))
        .content("좋아요")
        .userId(fromString("a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    reviewService.create(eventAddRequest);

    final List<Point> points = pointRepository.findByReviewId(
        fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"));

    assertThat(points.size()).isEqualTo(1);
    assertThat(points.get(0).getAmount()).isEqualTo(2);
  }

  @Test
  @DisplayName("글1자이상 & 사진1장이상 & 특정 장소 첫리뷰가 아니면 2점 얻어야 한다.")
  void shouldGet2PointWhenTextAndPhotos(){

    final EventRequest eventAddRequest = EventRequest.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId(fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"))
        .content("좋아요")
        .attachedPhotoIds(of(fromString("b546cc36-f713-45e2-8101-2a9831831301")))
        .userId(fromString("a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    reviewService.create(eventAddRequest);

    final EventRequest eventAddRequest2 = EventRequest.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId(fromString("99c51ed9-98da-44d7-ae4f-165d2c910665"))
        .content("좋아요1")
        .attachedPhotoIds(of(fromString("afbd7de1-b3e8-45e5-bff6-b7f34842ac1c")))
        .userId(fromString("63912dd1-71fb-40bc-ab1f-394b96b93ad8"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    reviewService.create(eventAddRequest2);

    final List<Point> points = pointRepository.findByReviewId(
        fromString("99c51ed9-98da-44d7-ae4f-165d2c910665"));

    assertThat(points.size()).isEqualTo(1);
    assertThat(points.get(0).getAmount()).isEqualTo(2);
  }

  @Test
  @DisplayName("글1자이상 & 사진1장이상 & 특정 장소 첫리뷰인 경우 3점 얻어야 한다.")
  void shouldGet3PointWhenTextAndPhotosAndNewPlace(){

    final EventRequest eventAddRequest = EventRequest.builder()
        .type("REVIEW")
        .action("ADD")
        .reviewId(fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"))
        .content("좋아요")
        .attachedPhotoIds(of(fromString("b546cc36-f713-45e2-8101-2a9831831301")))
        .userId(fromString("a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    reviewService.create(eventAddRequest);

    final List<Point> points = pointRepository.findByReviewId(
        fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"));

    assertThat(points.size()).isEqualTo(1);
    assertThat(points.get(0).getAmount()).isEqualTo(3);
  }

  @Test
  @DisplayName("지원하지 않는 action의 경우 IllegalArgumentException 발생")
  void shouldUnsupportedOperationExceptionWhenUnsupportedRequestActionType(){
    final EventRequest eventAddRequest = EventRequest.builder()
        .type("REVIEW")
        .action("ADD1")
        .reviewId(fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"))
        .content("좋아요")
        .attachedPhotoIds(of(fromString("b546cc36-f713-45e2-8101-2a9831831301")))
        .userId(fromString("a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    assertThatThrownBy(
        ()-> reviewService.handle(eventAddRequest)
    ).isInstanceOf(IllegalArgumentException.class);
  }



}