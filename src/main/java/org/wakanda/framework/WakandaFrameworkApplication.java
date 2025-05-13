/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.data.RepositoryMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus.PrometheusMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.web.servlet.WebMvcMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.wakanda.framework.entity.BaseEntity;

@SpringBootApplication(
    scanBasePackages = {"org.wakanda.framework"},
    exclude = {
      SecurityAutoConfiguration.class,
      ManagementWebSecurityAutoConfiguration.class,
      WebMvcMetricsAutoConfiguration.class,
      LiquibaseAutoConfiguration.class,
      RepositoryMetricsAutoConfiguration.class,
      RabbitAutoConfiguration.class,
      PrometheusMetricsExportAutoConfiguration.class,
      HealthEndpointAutoConfiguration.class,
      ElasticsearchRestClientAutoConfiguration.class
    })
@EntityScan(basePackageClasses = BaseEntity.class)
public class WakandaFrameworkApplication {
  public static void main(String[] args) {
    SpringApplication.run(WakandaFrameworkApplication.class, args);
  }
}
