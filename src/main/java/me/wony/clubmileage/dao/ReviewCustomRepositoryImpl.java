package me.wony.clubmileage.dao;

import static me.wony.clubmileage.entity.QReview.review;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{

  private final JPAQueryFactory queryFactory;


  @Override
  public boolean existsDuplicateReviewAtPlace(final UUID userId, final UUID placeId) {

    return queryFactory
        .selectFrom(review)
        .where(userIdEq(userId),placeIdEq(placeId))
        .fetchFirst() != null;
  }

  private BooleanExpression userIdEq(UUID userId){
    return userId == null ? null : review.user.id.eq(userId);
  }

  private BooleanExpression placeIdEq(UUID placeId){
    return placeId == null ? null : review.place.id.eq(placeId);
  }

  @Override
  public boolean existsReviewAtPlace(final UUID placeId) {
    return queryFactory
        .selectFrom(review)
        .where(review.place.id.eq(placeId))
        .fetchFirst() != null;
  }
}
