package com.moderation.comment.api.client.config.web;

import com.moderation.comment.api.client.exception.CommentClientBadGatewayException;
import com.moderation.comment.domain.service.exception.CommentNotFoundException;
import com.moderation.comment.domain.service.exception.ModerationServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({
          SocketTimeoutException.class,
          ConnectException.class,
          ClosedChannelException.class
  })
  public ProblemDetail handle(IOException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);
    problemDetail.setTitle("Gateway timeout");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/gateway-timout"));
    return problemDetail;
  }

  @ExceptionHandler({CommentClientBadGatewayException.class})
  public ProblemDetail handle(CommentClientBadGatewayException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
    problemDetail.setTitle("Bad Gateway");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/bad-gateway"));
    return problemDetail;
  }

  @ExceptionHandler({ModerationServiceException.class})
  public ProblemDetail handle(ModerationServiceException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    problemDetail.setTitle("Unprocessable Entity");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/unprocessable-entity"));
    return problemDetail;
  }

  @ExceptionHandler({CommentNotFoundException.class})
  public ProblemDetail handle(CommentNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("Not Found");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/not-found"));
    return problemDetail;
  }

}
