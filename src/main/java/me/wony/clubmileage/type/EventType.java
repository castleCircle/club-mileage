package me.wony.clubmileage.type;

import java.util.Arrays;

public enum EventType {
  REVIEW;

  public static EventType ofCode(final String code){
    return Arrays.stream(EventType.values())
        .filter(typeCode -> typeCode.name().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
