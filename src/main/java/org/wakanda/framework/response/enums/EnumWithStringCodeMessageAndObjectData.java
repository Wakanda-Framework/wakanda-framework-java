/* (C)2022 */
package org.wakanda.framework.response.enums;

/**
 * @author Aditya Kumar
 * @date 2022-05-17
 * @since JDK 17
 */
public interface EnumWithStringCodeMessageAndObjectData {
  String code();

  String message();

  Object data();
}
