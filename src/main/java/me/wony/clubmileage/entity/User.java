package me.wony.clubmileage.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private String name;

  public User(final UUID id,final String name){
    this.id = id;
    this.name = name;
  }

}
