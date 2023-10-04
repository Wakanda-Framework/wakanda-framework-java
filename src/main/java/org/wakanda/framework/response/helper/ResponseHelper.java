/* (C)2022 */
package org.wakanda.framework.response.helper;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.wakanda.framework.response.dto.ResponseDTO;
import org.wakanda.framework.response.enums.ResponseType;

/**
 * @author Vipul Meehnia
 * @updatedBy Aditya Kumar
 * @date 2021-11-10
 * @since JDK1.8
 */
@Component
public class ResponseHelper<T> implements ErrorResponseHelper<T>, SuccessResponseHelper<T> {

  @Override
  public ResponseEntity<ResponseDTO<T>> serverError() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(
            new ResponseDTO<>(
                ResponseType.SERVER_ERROR.code(), ResponseType.SERVER_ERROR.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> serverError(String message) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(new ResponseDTO<>(ResponseType.SERVER_ERROR.code(), message));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> serverError(T t) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(
            new ResponseDTO<>(
                ResponseType.SERVER_ERROR.code(), ResponseType.SERVER_ERROR.message(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> serverError(String message, T t) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(new ResponseDTO<>(ResponseType.SERVER_ERROR.code(), message, t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> clientError(ResponseType type) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .body(new ResponseDTO<>(type.code(), type.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, String message) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .body(new ResponseDTO<>(type.code(), message));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, T t) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .body(new ResponseDTO<>(type.code(), type.message(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, String message, T t) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .body(new ResponseDTO<>(type.code(), message, t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type) {
    return ResponseEntity.status(status.value())
        .body(new ResponseDTO<>(type.code(), type.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> error(
      HttpStatus status, ResponseType type, String message) {
    return ResponseEntity.status(status.value()).body(new ResponseDTO<>(type.code(), message));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type, T t) {
    return ResponseEntity.status(status.value())
        .body(new ResponseDTO<>(type.code(), type.message(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> error(
      HttpStatus status, ResponseType type, String message, T t) {
    return ResponseEntity.status(status.value()).body(new ResponseDTO<>(type.code(), message, t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> unknownError() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(
            new ResponseDTO<>(
                ResponseType.UNKNOWN_ERROR.code(), ResponseType.UNKNOWN_ERROR.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> ok() {
    return ResponseEntity.status(HttpStatus.OK.value())
        .body(new ResponseDTO<>(ResponseType.OK.code(), ResponseType.OK.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> ok(String message) {
    return ResponseEntity.status(HttpStatus.OK.value())
        .body(new ResponseDTO<>(ResponseType.OK.code(), message));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> ok(T t) {
    return ResponseEntity.status(HttpStatus.OK.value())
        .body(new ResponseDTO<>(ResponseType.OK.code(), ResponseType.OK.message(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> ok(String message, T t) {
    return ResponseEntity.status(HttpStatus.OK.value())
        .body(new ResponseDTO<>(ResponseType.OK.code(), message, t));
  }

  @Override
  public ResponseEntity<ResponseDTO<List<T>>> ok(List<T> t) {
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(ResponseType.OK.code(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type) {
    return ResponseEntity.status(status.value())
        .body(new ResponseDTO<>(type.code(), type.message()));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> success(
      HttpStatus status, ResponseType type, String message) {
    return ResponseEntity.status(status.value()).body(new ResponseDTO<>(type.code(), message));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type, T t) {
    return ResponseEntity.status(status.value())
        .body(new ResponseDTO<>(type.code(), type.message(), t));
  }

  @Override
  public ResponseEntity<ResponseDTO<T>> success(
      HttpStatus status, ResponseType type, String message, T t) {
    return ResponseEntity.status(status.value()).body(new ResponseDTO<>(type.code(), message, t));
  }

  public ResponseEntity<ResponseDTO<Page<T>>> ok(Page<T> all) {
    ResponseDTO<Page<T>> responseDTO = new ResponseDTO<>();
    responseDTO.setData(all);
    return ResponseEntity.ok(responseDTO);
  }
}
