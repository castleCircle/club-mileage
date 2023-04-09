package me.wony.clubmileage.dao;

import java.util.UUID;
import me.wony.clubmileage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, UUID> , ReviewCustomRepository{

  boolean existsByUserIdAndPlaceId(UUID userId, UUID placeId);

  boolean existsByPlaceId(UUID placeId);
}
