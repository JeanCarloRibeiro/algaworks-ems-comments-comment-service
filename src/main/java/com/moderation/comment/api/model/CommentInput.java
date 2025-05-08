package com.moderation.comment.api.model;

import lombok.Data;

@Data
public class CommentInput {
  private String text;
  private String author;
}
