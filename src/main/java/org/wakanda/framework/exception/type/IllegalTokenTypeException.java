/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception.type;

import java.io.Serial;
import org.wakanda.framework.enums.ErrorType;
import org.wakanda.framework.tools.BaseStaticValues;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class IllegalTokenTypeException extends BaseException {

  @Serial private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  public IllegalTokenTypeException() {
    super(ErrorType.ILLEGAL_TOKEN_TYPE_ERROR);
  }
}
