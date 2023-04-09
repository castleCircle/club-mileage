package me.wony.clubmileage.service;

import static java.util.Map.entry;
import static me.wony.clubmileage.type.EventType.ofCode;

import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dto.request.EventRequestDto;
import me.wony.clubmileage.dto.response.EventResponseDto;
import me.wony.clubmileage.type.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

  private final ReviewService reviewService;

  private Map<EventType, Function<EventRequestDto, EventResponseDto>> eventHandler;

  @PostConstruct
  public void init(){
    eventHandler =
        Map.ofEntries(
            entry(EventType.REVIEW,reviewService::handle)
        );
  }

  private EventResponseDto exception(){
    throw new UnsupportedOperationException("요청 사항을 확인해주세요!");
  }

  @Transactional
  public EventResponseDto handle(final EventRequestDto dto){
    return eventHandler.getOrDefault(ofCode(dto.getType()),(v)->exception()).apply(dto);
  }



}
