package com.moderation.comment.domain.service;

import com.moderation.comment.api.client.ModerateCommentClient;
import com.moderation.comment.api.model.CommentId;
import com.moderation.comment.api.model.CommentInput;
import com.moderation.comment.api.model.CommentOutput;
import com.moderation.comment.api.model.ModerationInput;
import com.moderation.comment.api.model.ModerationOutput;
import com.moderation.comment.common.IdGenerator;
import com.moderation.comment.domain.model.Comment;
import com.moderation.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository repository;
  private final ModerateCommentClient moderateCommentClient;

  public CommentOutput saveComment(CommentInput input) {
    UUID commentId = IdGenerator.generateTimeBasedUUID();
    Comment comment = Comment.builder()
            .id(new CommentId(commentId))
            .author(input.getAuthor())
            .text(input.getText())
            .createdAt(OffsetDateTime.now())
            .build();

    ModerationInput moderateInput = ModerationInput.builder()
            .text(input.getText())
            .commentId(commentId.toString())
            .build();

    ModerationOutput moderateOutput = moderateCommentClient.approveModerate(moderateInput);
    log.info("ModerateOutput: {}", moderateOutput);

    if (moderateOutput != null && moderateOutput.getApproved().equals(true)) {
      comment = repository.saveAndFlush(comment);
      log.info("Successfully created comment with id {}.", comment.getId());
    } else {
      log.info("Failure to create comment.");
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    return convertToModel(comment);
  }

  public CommentOutput findComment(UUID commentId) {
    Comment comment = repository.findById(new CommentId(commentId)).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return convertToModel(comment);
  }

  public Page<CommentOutput> findComments(Pageable pageable) {
    Page<Comment> comments = repository.findAll(pageable);
    if (comments.getTotalElements() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return comments.map(this::convertToModel);
  }

  private CommentOutput convertToModel(Comment comment) {
    return CommentOutput.builder()
            .id(comment.getId().toString())
            .text(comment.getText())
            .author(comment.getAuthor())
            .createdAt(comment.getCreatedAt())
            .build();
  }

}
