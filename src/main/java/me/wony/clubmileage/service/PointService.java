package me.wony.clubmileage.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dao.PointRepository;
import me.wony.clubmileage.dao.UserRepository;
import me.wony.clubmileage.dto.response.PointHistoryResponse;
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

  public List<PointHistoryResponse> getPointHistories(){
    return pointRepository.find(null)
        .stream()
        .map(PointHistoryResponse::of)
        .collect(Collectors.toList());
  }

  public List<PointHistoryResponse> getPointHistoriesByUserId(final String userId){

    UUID uuidUserId = UUID.fromString(userId);

    userRepository.findById(uuidUserId)
        .orElseThrow(
            () -> new ResourceNotFoundException("userId: " + uuidUserId + "cannot be found"));

    return pointRepository.find(uuidUserId)
        .stream()
        .map(PointHistoryResponse::of)
        .collect(Collectors.toList());
  }

}
