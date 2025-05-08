package com.moderation.comment.domain.model;

import com.moderation.comment.api.model.CommentId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Comment {
  @Id
  @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "UUID"))
  private CommentId id;
  private String text;
  private String author;
  @CreatedDate
  private OffsetDateTime createdAt;
}
