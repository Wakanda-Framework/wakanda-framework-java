/* (C)2022 */
package org.wakanda.framework.response.helper;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wakanda.framework.response.dto.ResponseDTO;
import org.wakanda.framework.response.enums.ResponseType;

/**
 * @author Vipul Meehnia
 * @date 2021-11-10
 * @since JDK1.8
 */
public interface SuccessResponseHelper<T> {

  ResponseEntity<ResponseDTO<T>> ok();

  ResponseEntity<ResponseDTO<T>> ok(String message);

  ResponseEntity<ResponseDTO<T>> ok(T t);

  ResponseEntity<ResponseDTO<T>> ok(String message, T t);

  ResponseEntity<ResponseDTO<List<T>>> ok(List<T> t);

  ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type);

  ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type, String message);

  ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type, T t);

  ResponseEntity<ResponseDTO<T>> success(HttpStatus status, ResponseType type, String message, T t);
}
