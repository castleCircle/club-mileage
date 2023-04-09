package me.wony.clubmileage.dao;

import java.util.UUID;
import me.wony.clubmileage.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, UUID> {

}