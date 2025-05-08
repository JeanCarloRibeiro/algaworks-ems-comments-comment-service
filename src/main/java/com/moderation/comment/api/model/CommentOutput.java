package com.moderation.comment.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data @Builder
public class CommentOutput {
  private String id;
  private String text;
  private String author;
  private OffsetDateTime createdAt;
}
