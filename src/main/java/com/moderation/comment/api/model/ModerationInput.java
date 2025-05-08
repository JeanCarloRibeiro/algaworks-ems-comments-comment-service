package com.moderation.comment.api.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ModerationInput {
  private String text;
  private String commentId;
}
