package me.wony.clubmileage.dao;

import static me.wony.clubmileage.entity.QPoint.point;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
            point.user.id.eq(userID),
            point.status.eq(true)
          )
        .fetchOne();
  }
}
