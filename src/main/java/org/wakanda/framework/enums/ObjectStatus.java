/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public enum ObjectStatus implements EnumWithCode {
  INVALID(0),
  VALID(1);

  private final Integer code;

  ObjectStatus(Integer code) {
    this.code = code;
  }

  @Override
  public Integer code() {
    return code;
  }
}
