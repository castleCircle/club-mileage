package me.wony.clubmileage.type;

import java.util.Arrays;

public enum EventActionType {
  ADD,MOD,DELETE;

  public static EventActionType ofCode(final String code){
    return Arrays.stream(EventActionType.values())
        .filter(typeCode -> typeCode.name().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
