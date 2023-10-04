/* (C)2022 */
package org.wakanda.framework.response.dto;

import java.io.Serial;
import java.io.Serializable;

import org.wakanda.framework.tools.BaseStaticValues;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aditya Kumar
 * @date 2022-05-17
 * @since JDK 17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {

  @Serial private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  private Integer code;
  private String description;

  public ErrorDTO(Integer code) {
    this.code = code;
  }
}
