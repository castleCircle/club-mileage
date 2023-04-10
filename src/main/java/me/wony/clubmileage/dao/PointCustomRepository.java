package me.wony.clubmileage.dao;

import java.util.List;
import java.util.UUID;
import me.wony.clubmileage.entity.User;

public interface PointCustomRepository {

  Integer getPointByUserId(UUID userID);

  Integer getPointByReviewId(UUID reviewID);

  List<User> find(UUID userId);
}
