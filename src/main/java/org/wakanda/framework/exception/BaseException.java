/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import java.io.Serial;
import lombok.Getter;
import lombok.Setter;
import org.wakanda.framework.enums.EnumWithCodeAndDescription;
import org.wakanda.framework.tools.BaseStaticValues;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 * @serialVersionUID - The serialVersionUID attribute is an identifier that is used to
 *     serialize/deserialize an object of a Serializable class.
 */
@Getter
@Setter
public class BaseException extends RuntimeException {
  @Serial private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  private int code;
  private String message;

  public BaseException(EnumWithCodeAndDescription errorType) {
    super(errorType.msg());
    this.code = errorType.code();
    this.message = errorType.msg();
  }

  public BaseException(int code, String msg) {
    super(msg);
    this.code = code;
    this.message = msg;
  }

  public BaseException(int code, String msg, Throwable cause) {
    super(msg, cause);
    this.code = code;
    this.message = msg;
  }

  public BaseException(EnumWithCodeAndDescription errorType, Throwable cause) {
    super(errorType.msg(), cause);
    this.code = errorType.code();
    this.message = errorType.msg();
  }

  public int code() {
    return code;
  }

  public String message() {
    return message;
  }
}
