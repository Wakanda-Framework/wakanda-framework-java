/* (C)2022 */
package org.wakanda.framework.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;

import org.wakanda.framework.tools.BaseStaticValues;

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
public class ResponseDTO<T> implements Serializable {
  @Serial @JsonIgnore
  private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  private String code;
  private String message;
  private T data;

  public ResponseDTO(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResponseDTO(String message, T data) {
    this.message = message;
    this.data = data;
  }
}
