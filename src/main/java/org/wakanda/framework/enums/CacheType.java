/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums;

/**
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public enum CacheType {
  REDIS,
  MAP;

  public boolean isRedis() {
    return name().equals(REDIS.name());
  }

  public boolean isMap() {
    return name().equals(MAP.name());
  }
}
