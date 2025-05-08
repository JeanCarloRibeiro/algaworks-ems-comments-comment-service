package com.moderation.comment.api.client;

import com.moderation.comment.api.model.ModerationInput;
import com.moderation.comment.api.model.ModerationOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface ModerateCommentClient {
  @PostExchange
  ModerationOutput approveModerate(@RequestBody ModerationInput input);
}
