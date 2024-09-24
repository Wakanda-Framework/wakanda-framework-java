/* (C) 2024 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import org.wakanda.framework.enums.ErrorType;

public class PreProcessingException extends BaseException {

  private static final long serialVersionUID = 7181557948331449458L;

  public PreProcessingException() {
    super(ErrorType.PRE_PROCESSING_ERROR);
  }

  public PreProcessingException(String msg) {
    super(ErrorType.PRE_PROCESSING_ERROR.code(), msg);
  }

  public PreProcessingException(ErrorType errorType) {
    super(errorType.code(), errorType.msg());
  }
}
