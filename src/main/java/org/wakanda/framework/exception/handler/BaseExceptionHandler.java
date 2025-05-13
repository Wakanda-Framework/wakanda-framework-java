/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception.handler;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.wakanda.framework.enums.ErrorType;
import org.wakanda.framework.exception.BaseException;
import org.wakanda.framework.exception.IllegalExtTypeException;
import org.wakanda.framework.exception.IllegalParamException;
import org.wakanda.framework.exception.IllegalTokenTypeException;
import org.wakanda.framework.exception.PostProcessingException;
import org.wakanda.framework.exception.PreProcessingException;
import org.wakanda.framework.exception.SignFailedException;
import org.wakanda.framework.exception.handler.response.BaseExceptionResponse;

@Slf4j
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  @NonNull
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatus status,
      @NonNull WebRequest request) {

    Map<String, String> errors =
        exception.getBindingResult().getAllErrors().stream()
            .collect(
                Collectors.toMap(
                    objectError -> ((FieldError) objectError).getField(),
                    DefaultMessageSourceResolvable::getDefaultMessage));

    log.error("[BaseExceptionHandler] Validation error : Reason - {} ", errors);

    BaseExceptionResponse baseExceptionResponse =
        BaseExceptionResponse.builder()
            .code(ErrorType.ILLEGAL_PARAM_ERROR.code())
            .message(
                errors.keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "))
                    .concat(" is invalid."))
            .payload(errors.values().toArray())
            .build();
    return ResponseEntity.unprocessableEntity().body(baseExceptionResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      Exception e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] Generic Exception: ", e);
    return ResponseEntity.internalServerError()
        .body(
            BaseExceptionResponse.builder()
                .code(ErrorType.SERVER_ERROR.code())
                .message(ErrorType.SERVER_ERROR.msg())
                .payload(e.getMessage())
                .build());
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      BaseException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] Base Exception: ", e);
    return ResponseEntity.internalServerError()
        .body(
            BaseExceptionResponse.builder()
                .code(e.code())
                .message(e.message())
                .payload(e.getMessage())
                .build());
  }

  @ExceptionHandler(IllegalExtTypeException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      IllegalExtTypeException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] IllegalExtType Exception: ", e);
    return ResponseEntity.unprocessableEntity()
        .body(
            BaseExceptionResponse.builder()
                .code(e.code())
                .message(e.message())
                .payload(e.getMessage())
                .build());
  }

  @ExceptionHandler(IllegalParamException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      IllegalParamException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] IllegalParam Exception: ", e);
    return ResponseEntity.unprocessableEntity()
        .body(
            BaseExceptionResponse.builder()
                .code(e.code())
                .message(e.message())
                .payload(e.getMessage())
                .build());
  }

  @ExceptionHandler(IllegalTokenTypeException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      IllegalTokenTypeException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] IllegalTokenType Exception: ", e);
    return ResponseEntity.unprocessableEntity()
        .body(
            BaseExceptionResponse.builder()
                .code(e.code())
                .message(e.message())
                .payload(e.getMessage())
                .build());
  }

  @ExceptionHandler(SignFailedException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      SignFailedException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] SignFailed Exception: ", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(e.code())
            .message(e.message())
            .payload(e.getMessage())
            .build(),
        HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(OptimisticEntityLockException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      OptimisticEntityLockException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] OptimisticEntityLock Exception: ", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(ErrorType.OPTIMISTIC_LOCKING_EXCEPTION.code())
            .message(ErrorType.OPTIMISTIC_LOCKING_EXCEPTION.msg())
            .payload(
                "[OptimisticEntityLockException] Entity has already been changed to"
                    + " different version")
            .build(),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      ObjectOptimisticLockingFailureException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] ObjectOptimisticLockingFailure Exception: ", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(ErrorType.OPTIMISTIC_LOCKING_EXCEPTION.code())
            .message(ErrorType.OPTIMISTIC_LOCKING_EXCEPTION.msg())
            .payload(
                "[ObjectOptimisticLockingFailureException] Entity has already been changed to"
                    + " different version")
            .build(),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler({JpaObjectRetrievalFailureException.class})
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      JpaObjectRetrievalFailureException e, WebRequest webRequest) {
    log.error("[JpaSystemException] JPA entity retrieval failure", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(ErrorType.NO_SUCH_ELEMENT_ERROR.code())
            .message(ErrorType.NO_SUCH_ELEMENT_ERROR.msg())
            .payload(e.getMostSpecificCause().getLocalizedMessage())
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PreProcessingException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      PreProcessingException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] Pre-Processing Exception: ", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(e.code())
            .message(e.message())
            .payload(e.getMessage())
            .build(),
        HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(PostProcessingException.class)
  public ResponseEntity<BaseExceptionResponse> handleExceptions(
      PostProcessingException e, WebRequest webRequest) {
    log.error("[BaseExceptionHandler] Post-Processing Exception: ", e);
    return new ResponseEntity<>(
        BaseExceptionResponse.builder()
            .code(e.code())
            .message(e.message())
            .payload(e.getMessage())
            .build(),
        HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
