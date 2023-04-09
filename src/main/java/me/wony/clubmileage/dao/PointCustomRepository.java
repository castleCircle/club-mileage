package me.wony.clubmileage.dao;

import java.util.UUID;

public interface PointCustomRepository {

  Integer getPointByUserId(UUID userID);

}
