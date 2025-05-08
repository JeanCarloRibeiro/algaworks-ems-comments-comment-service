package com.moderation.comment.api.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentId implements Serializable {

  private UUID value;

  public CommentId(UUID id) {
    this.value = id;
  }

  public CommentId(String value) {
    Objects.requireNonNull(value);
    this.value = UUID.fromString(value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
