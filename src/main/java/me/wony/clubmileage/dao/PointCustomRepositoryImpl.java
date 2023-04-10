package me.wony.clubmileage.dao;

import static me.wony.clubmileage.entity.QPoint.point;
import static me.wony.clubmileage.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointCustomRepositoryImpl implements PointCustomRepository{

  private final JPAQueryFactory queryFactory;

  @Override
  public Integer getPointByUserId(UUID userID) {
    return queryFactory
        .select(point.amount.sum().coalesce(0))
        .from(point)
        .where(
            point.user.id.eq(userID)
          )
        .fetchOne();
  }

  @Override
  public Integer getPointByReviewId(UUID reviewID) {
    return queryFactory
        .select(point.amount.sum().coalesce(0))
        .from(point)
        .where(
            point.review.id.eq(reviewID)
        )
        .fetchOne();
  }

  @Override
  public List<User> find(UUID userId) {
    return queryFactory
        .select(user)
        .from(user)
        .leftJoin(user.points, point).fetchJoin()
        .where(
            userIdEq(userId)
        )
        .distinct()
        .fetch();
  }

  private BooleanExpression userIdEq(UUID userId){
    return userId == null ? null : user.id.eq(userId);
  }
}
