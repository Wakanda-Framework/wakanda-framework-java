/* (C)2022 */
package org.wakanda.framework.response.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wakanda.framework.response.dto.ResponseDTO;
import org.wakanda.framework.response.enums.ResponseType;

/**
 * @author Vipul Meehnia
 * @date 2021-11-10
 * @since JDK1.8
 */
public interface ErrorResponseHelper<T> {

  ResponseEntity<ResponseDTO<T>> serverError();

  ResponseEntity<ResponseDTO<T>> serverError(String message);

  ResponseEntity<ResponseDTO<T>> serverError(T t);

  ResponseEntity<ResponseDTO<T>> serverError(String message, T t);

  ResponseEntity<ResponseDTO<T>> clientError(ResponseType type);

  ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, String message);

  ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, T t);

  ResponseEntity<ResponseDTO<T>> clientError(ResponseType type, String message, T t);

  ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type);

  ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type, String message);

  ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type, T t);

  ResponseEntity<ResponseDTO<T>> error(HttpStatus status, ResponseType type, String message, T t);

  ResponseEntity<ResponseDTO<T>> unknownError();
}
