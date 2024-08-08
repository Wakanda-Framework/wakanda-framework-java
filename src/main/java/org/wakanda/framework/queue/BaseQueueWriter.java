/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vipulmeehnia
 * @date 2021-09-15
 * @since JDK1.8
 */
public class BaseQueueWriter {

  @Autowired private RabbitTemplate rabbitTemplate;

  public void writeToQueue(String exchange, String routingKey, Object object) {
    rabbitTemplate.convertAndSend(exchange, routingKey, object);
  }
}
