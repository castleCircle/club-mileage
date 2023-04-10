package me.wony.clubmileage.service;

import static java.util.Set.of;
import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import me.wony.clubmileage.dto.request.EventRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class EventServiceTest {

  @Autowired
  private EventService eventService;

  @Test
  @DisplayName("지원하지 않는 type의 경우 IllegalArgumentException 발생")
  void shouldUnsupportedOperationExceptionWhenUnsupportedRequestActionType(){
    final EventRequest eventAddRequest = EventRequest.builder()
        .type("REVIEW1")
        .action("ADD")
        .reviewId(fromString("90490cfc-c0bc-471b-80e7-9fe4086c62d0"))
        .content("좋아요")
        .attachedPhotoIds(of(fromString("b546cc36-f713-45e2-8101-2a9831831301")))
        .userId(fromString("a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3"))
        .placeId(fromString("a542e928-30e4-4256-86c5-e18cfb78b385"))
        .build();

    assertThatThrownBy(
        ()-> eventService.handle(eventAddRequest)
    ).isInstanceOf(IllegalArgumentException.class);
  }

}