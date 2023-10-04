/* (C)2022 */
package org.wakanda.framework.tools;

/**
 * Custom assert tool.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class Assert {

  private Assert() {}

  public static void defaultNotNull(Object obj) {
    final String IS_NULL = " is null";
    org.springframework.util.Assert.notNull(obj, obj.getClass().getSimpleName() + IS_NULL);
  }
}
