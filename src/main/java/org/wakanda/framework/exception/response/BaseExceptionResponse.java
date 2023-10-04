/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseExceptionResponse {
  Integer code;
  String message;
  Object payload;
}
