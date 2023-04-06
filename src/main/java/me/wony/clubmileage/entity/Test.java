package me.wony.clubmileage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {

  @Id @GeneratedValue
  private Long id;
}
