package me.wony.clubmileage.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{

  @Id
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="place_id")
  private Place place;

  @Column
  private String content;

  @OneToMany(mappedBy = "review", orphanRemoval = true ,cascade = CascadeType.ALL)
  private Set<Photo> attachedPhotos = new HashSet<>();

  @Builder
  public Review(UUID id, User user, Place place, String content) {
    this.id = id;
    this.user = user;
    this.place = place;
    this.content = content;
  }

  public void addPhoto(Photo photo){
    attachedPhotos.add(photo);
    photo.addReview(this);
  }

  public void deletePhoto(Photo photo){
    attachedPhotos.remove(photo);
  }

  public void changeContent(String content){
    this.content = content;
  }

  public boolean hasAttachedPhoto(){
    return !attachedPhotos.isEmpty();
  }

}
