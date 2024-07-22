/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vipulmeehnia
 * @date 2021-09-15
 * @since JDK1.8
 */
@Component
public class QueueWriter {

  @Autowired private RabbitTemplate rabbitTemplate;

  public void writeToQueue(String exchange, String routingKey, Object object) {
    rabbitTemplate.convertAndSend(exchange, routingKey, object);
  }
}
