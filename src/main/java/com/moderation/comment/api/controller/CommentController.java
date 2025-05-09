package com.moderation.comment.api.controller;

import com.moderation.comment.api.model.CommentInput;
import com.moderation.comment.api.model.CommentOutput;
import com.moderation.comment.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public CommentOutput create(@RequestBody CommentInput input) {
    if (input == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return commentService.saveComment(input);
  }

  @GetMapping("/{commentId}")
  @ResponseStatus(HttpStatus.OK)
  public CommentOutput getOne(@PathVariable UUID commentId) {
    CommentOutput comment = commentService.findComment(commentId);
    if (comment == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return comment;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<CommentOutput> searchComments(@PageableDefault Pageable pageable) {
    Page<CommentOutput> comment = commentService.searchComment(pageable);
    if (comment.getTotalElements() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return comment;
  }

}
