/* (C) 2024 WAKANDA FRAMEWORK */
package org.wakanda.framework.exception;

import org.wakanda.framework.enums.ErrorType;

public class PostProcessingException extends BaseException {
  private static final long serialVersionUID = 5073058148164181657L;

  public PostProcessingException() {
    super(ErrorType.POST_PROCESSING_ERROR);
  }

  public PostProcessingException(String msg) {
    super(ErrorType.POST_PROCESSING_ERROR.code(), msg);
  }
}
