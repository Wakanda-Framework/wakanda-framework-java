/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import java.io.Serial;
import org.wakanda.framework.enums.ErrorType;
import org.wakanda.framework.tools.BaseStaticValues;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class SignFailedException extends BaseException {

  @Serial private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  public SignFailedException() {
    super(ErrorType.LOGIN_FAILED);
  }
}
