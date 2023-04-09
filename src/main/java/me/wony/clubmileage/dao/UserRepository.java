package me.wony.clubmileage.dao;

import java.util.UUID;
import me.wony.clubmileage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

}
