/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import org.wakanda.framework.enums.ErrorType;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class IllegalExtTypeException extends BaseException {

  private static final long serialVersionUID = 8253771687970781555L;

  public IllegalExtTypeException() {
    super(ErrorType.ILLEGAL_TOKEN_TYPE_ERROR);
  }

  public IllegalExtTypeException(String msg) {
    super(ErrorType.ILLEGAL_EXT_TYPE_ERROR.code(), msg);
  }
}
