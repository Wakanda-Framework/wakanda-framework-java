/* (C)2022 */
package org.wakanda.framework.mailer;

/**
 * @author vipulmeehnia
 * @date 2021-09-15
 * @since JDK1.8
 */
public class MailAttachment {

  private String name;
  private String path;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
