/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis for caching config.
 *
 * @author Vipul Meehnia
 * @date 8/18/21
 * @since JDK1.8
 */
@Configuration
@EnableCaching
@Order(1)
public class RedisConfig extends CachingConfigurerSupport {

  @Bean(name = "genericRedisTemplate")
  public RedisTemplate<String, Object> genericRedisTemplate(RedisConnectionFactory rcf) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    redisTemplate.setEnableTransactionSupport(true);
    redisTemplate.setConnectionFactory(rcf);
    return redisTemplate;
  }
}
