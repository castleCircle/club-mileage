package me.wony.clubmileage.controller;

import static me.wony.clubmileage.dto.response.common.ApiResponse.payload;

import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dto.response.common.ApiResponse;
import me.wony.clubmileage.service.PointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

  private final PointService pointService;

  @GetMapping("/users/{userId}")
  public ApiResponse<Integer> getPointByUserId(@PathVariable final String userId){
    return payload(pointService.getPointByUserId(userId));
  }

}
