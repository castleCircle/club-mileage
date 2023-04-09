package me.wony.clubmileage.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dao.PointRepository;
import me.wony.clubmileage.dao.UserRepository;
import me.wony.clubmileage.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {

  private final UserRepository userRepository;
  private final PointRepository pointRepository;

  public Integer getPointByUserId(final String userId){
    UUID uuidUserId = UUID.fromString(userId);

    userRepository.findById(uuidUserId)
        .orElseThrow(
            () -> new ResourceNotFoundException("userId: " + uuidUserId + "cannot be found"));

    return pointRepository.getPointByUserId(uuidUserId);
  }

}
