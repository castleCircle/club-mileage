package me.wony.clubmileage.dao;

import java.util.UUID;
import me.wony.clubmileage.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {

}
