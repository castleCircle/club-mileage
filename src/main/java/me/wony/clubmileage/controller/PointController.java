package me.wony.clubmileage.controller;

import lombok.RequiredArgsConstructor;
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
  public Integer getPointByUserId(@PathVariable final String userId){
    return pointService.getPointByUserId(userId);
  }

}
