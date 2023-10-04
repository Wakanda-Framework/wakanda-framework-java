/* (C)2022 */
package org.wakanda.framework.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

public class ApplicationContextAwarenessHandler implements ApplicationContextAware {
  private static ApplicationContext applicationContext = null;
  private static JdbcTemplate jdbcTemplate;
  private static Map<String, UserDetails> userDetailsMap;

  public static Long getUserId(String username) {
    if (userDetailsMap == null) {
      userDetailsMap = new ConcurrentHashMap<>();
    }

    if (userDetailsMap.containsKey(username)) {
      UserDetails userDetails = userDetailsMap.get(username);
      // Assuming 10 min timeout for user sessions TODO --> update this with product clarity.
      if (userDetails.getLastFetchedAt().isAfter(DateTime.now().minusMinutes(10))) {
        return userDetails.getUserId();
      }
    }
    final Long userId =
        jdbcTemplate.queryForObject(
            "select id from user_metadata where email=?", Long.class, username);
    UserDetails userDetailsRefreshed = new UserDetails(userId, DateTime.now());
    userDetailsMap.put(username, userDetailsRefreshed);
    return userId;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextAwarenessHandler.applicationContext = applicationContext;
    jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    userDetailsMap = new ConcurrentHashMap<>();
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  static class UserDetails {
    private Long userId;
    private DateTime lastFetchedAt;
  }
}
