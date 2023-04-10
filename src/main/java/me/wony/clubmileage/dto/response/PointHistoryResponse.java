package me.wony.clubmileage.dto.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.wony.clubmileage.entity.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryResponse {

  private UUID userId;
  private Set<PointHistories> pointHistories = new HashSet<>();

  @Data
  @Builder
  static class PointHistories{
    private UUID reviewId;
    private Integer amount;
    private LocalDateTime createdTime;
  }

  public static PointHistoryResponse of(final User user){
    return PointHistoryResponse.builder()
        .userId(user.getId())
        .pointHistories(
            user.getPoints().stream()
                .map(
                    p -> PointHistories.builder()
                        .reviewId(p.getReview() == null ? null : p.getReview().getId())
                        .amount(p.getAmount())
                        .createdTime(p.getCreatedTime())
                        .build()
                )
                .collect(Collectors.toSet())
        )
        .build();
  }

}
