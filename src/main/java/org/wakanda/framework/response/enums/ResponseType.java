/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.response.enums;

import lombok.AllArgsConstructor;

/**
 * @author Aditya Kumar
 * @date 2022-05-17
 * @since JDK 17
 */
@AllArgsConstructor
public enum ResponseType implements EnumWithStringCodeMessageAndObjectData {

  /** 2XX Responses */
  OK("WKF-S20000", "Success."),
  CREATED("WKF-S20100", "Data received."),
  REQUEST_ACCEPTED("WKF-20200", "Request has been accepted"),
  NO_CONTENT("WKF-20400", "No content"),
  PARTIAL_CONTENT("WKF-20600", "Partial content"),

  /** 4XX Responses */
  BAD_REQUEST("WKF-E40000", "Bad request"),
  FORBIDDEN("WKF-E40300", "Forbidden"),
  NOT_FOUND("WKF-E40400", "Not found"),
  METHOD_NOT_ALLOWED("WKF-E40500", "Method Not Allowed"),
  CONFLICT("WKF-E409", "Conflict"),
  ILLEGAL_ENTITY_ERROR("WKF-E42200", "Illegal Entity."),
  ILLEGAL_PARAM_ERROR("WKF-E42201", "Illegal param."),
  ILLEGAL_TOKEN_TYPE_ERROR("WKF-E42202", "Illegal token type."),
  ILLEGAL_EXT_TYPE_ERROR("WKF-E42203", "Illegal extension type."),
  SERVER_ERROR("WKF-E50000", "Server Error."),
  SIGN_FAILED("WKF-E50001", "Sign failed."),
  NOT_IMPLEMENTED("WKF-E50100", "Not implemented"),
  UNKNOWN_ERROR("WKF-E99999", "Unknown Error.");

  final String code;
  final String message;
  Object data;

  @Override
  public String code() {
    return code;
  }

  @Override
  public String message() {
    return message;
  }

  @Override
  public Object data() {
    return data;
  }

  ResponseType(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
