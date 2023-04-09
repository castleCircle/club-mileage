package me.wony.clubmileage.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {

  @CreatedBy
  private Long createdBy;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdTime;

  @LastModifiedBy
  private Long updatedBy;

  @LastModifiedDate
  private LocalDateTime updatedTime;

}
