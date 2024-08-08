/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import org.wakanda.framework.enums.ErrorType;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class IllegalTokenTypeException extends BaseException {
  private static final long serialVersionUID = -1989605824769030384L;

  public IllegalTokenTypeException() {
    super(ErrorType.ILLEGAL_TOKEN_TYPE_ERROR);
  }
}
