package me.wony.clubmileage.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dto.response.PointHistoryResponse;
import me.wony.clubmileage.service.PointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points/histories")
@RequiredArgsConstructor
public class PointHistoryController {

  private final PointService pointService;

  @GetMapping
  public List<PointHistoryResponse> getPointHistories(){
    return pointService.getPointHistories();
  }

  @GetMapping("/{userId}")
  public List<PointHistoryResponse> getPointHistoriesByUserId(@PathVariable final String userId){
    return pointService.getPointHistoriesByUserId(userId);
  }

}
