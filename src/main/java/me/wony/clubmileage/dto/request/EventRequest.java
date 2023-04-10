package me.wony.clubmileage.dto.request;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EventRequest {

  @NotNull
  private String type;

  @NotNull
  private String action;

  @NotNull
  private UUID reviewId;

  private String content;

  private Set<UUID> attachedPhotoIds = new HashSet<>();

  private UUID userId;

  private UUID placeId;

}
