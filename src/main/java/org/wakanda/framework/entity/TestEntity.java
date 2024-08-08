/* (C) 2024 WAKANDA FRAMEWORK */
package org.wakanda.framework.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "test_framework")
public class TestEntity extends BaseEntity<Integer> {

  private static final long serialVersionUID = 7598290688834811243L;

  private String test;
}
