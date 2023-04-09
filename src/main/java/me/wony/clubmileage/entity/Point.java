package me.wony.clubmileage.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.wony.clubmileage.type.PointType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity{

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "review_id")
  private Review review;

  @Enumerated(EnumType.STRING)
  private PointType type;

  private Integer amount;

  @Builder
  public Point(
      final UUID id,
      final User user,
      final Review review,
      final PointType type,
      final Integer amount
  ){
    this.id = id;
    this.user = user;
    this.review = review;
    this.type = type;
    this.amount = amount;
  }


  public static Point createPointOfReview(
      final User user,
      final Review review,
      final Integer amount
  ){
    return Point.builder()
        .id(UUID.randomUUID())
        .user(user)
        .review(review)
        .type(PointType.REVIEW)
        .amount(amount)
        .build();
  }

}
