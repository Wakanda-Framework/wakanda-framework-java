/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.constant;

/**
 * Constant for authority.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public abstract class AuthConstant {

  private AuthConstant() {}

  public static final String RESOURCE_ID = "api";
  public static final String SCOPE = "read";
  public static final String GRANT_TYPE = "password,refresh_token";
  public static final String AUTHORITY = "USER";
  public static final String BASIC = "basic";
  public static final String BEARER = "bearer";
  public static final String AUTHORIZATION = "Authorization";
}
