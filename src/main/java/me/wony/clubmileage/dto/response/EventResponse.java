package me.wony.clubmileage.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

  private UUID reviewId;

  public static EventResponse ofEmpty(){
    return new EventResponse();
  }
}
