package me.wony.clubmileage.dao;

import java.util.UUID;
import me.wony.clubmileage.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, UUID> {

}
