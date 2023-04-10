package me.wony.clubmileage.service;

import static java.util.Map.entry;
import static me.wony.clubmileage.type.EventType.ofCode;

import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dto.request.EventRequest;
import me.wony.clubmileage.dto.response.EventResponse;
import me.wony.clubmileage.type.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

  private final ReviewService reviewService;

  private Map<EventType, Function<EventRequest, EventResponse>> eventHandler;

  @PostConstruct
  public void init(){
    eventHandler =
        Map.ofEntries(
            entry(EventType.REVIEW,reviewService::handle)
        );
  }

  private EventResponse exception(){
    throw new UnsupportedOperationException("요청 사항을 확인해주세요!");
  }

  @Transactional
  public EventResponse handle(final EventRequest dto){
    return eventHandler.getOrDefault(ofCode(dto.getType()),(v)->exception()).apply(dto);
  }



}
