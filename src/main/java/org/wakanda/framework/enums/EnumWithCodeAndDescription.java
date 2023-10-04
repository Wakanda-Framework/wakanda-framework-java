/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums;

/**
 * Intent state interface.
 *
 * <pre>
 *     String code;
 *     String msg;
 * </pre>
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public interface EnumWithCodeAndDescription {

  Integer code();

  String msg();
}
