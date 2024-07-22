/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.wakanda.framework.entity.BaseEntity;

@SpringBootApplication
@EntityScan(basePackageClasses = BaseEntity.class)
public class WakandaFrameworkApplication {}
