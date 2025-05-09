package com.moderation.comment.domain.service.exception;

public class CommentNotFoundException extends RuntimeException {
  public CommentNotFoundException(String message) {
    super(message);
  }
}
