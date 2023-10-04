/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums;

/**
 * Operation status.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public enum GrantType implements EnumWithDescription {
  PASSWORD("password"),
  REFRESH_TOKEN("refresh_token"),
  AUTHORIZATION_CODE("authorization_code"),
  ;

  private final String description;

  GrantType(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return this.description;
  }
}
