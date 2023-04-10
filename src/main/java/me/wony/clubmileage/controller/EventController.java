package me.wony.clubmileage.controller;

import lombok.RequiredArgsConstructor;
import me.wony.clubmileage.dto.request.EventRequest;
import me.wony.clubmileage.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping
  public void handle(@RequestBody final EventRequest dto){
    eventService.handle(dto);
  }


}
