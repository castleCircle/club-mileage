package me.wony.clubmileage.dao;

import java.util.UUID;

public interface ReviewCustomRepository {

 boolean existsDuplicateReviewAtPlace(UUID userId, UUID placeId);

 boolean existsReviewAtPlace(UUID placeId);

}
