package me.wony.clubmileage.dao;

import java.util.List;
import java.util.UUID;
import me.wony.clubmileage.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, UUID> , PointCustomRepository{

  List<Point> findByReviewId(UUID reviewId);

}