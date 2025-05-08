package com.moderation.comment.domain.repository;

import com.moderation.comment.api.model.CommentId;
import com.moderation.comment.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

  Comment findCommentById(CommentId id);
}
