package me.wony.clubmileage.dto.request;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

  @NotNull
  private String type;

  @NotNull
  private String action;

  @NotNull
  private UUID reviewId;

  private String content;

  @Builder.Default
  private Set<UUID> attachedPhotoIds = new HashSet<>();

  private UUID userId;

  private UUID placeId;

}
