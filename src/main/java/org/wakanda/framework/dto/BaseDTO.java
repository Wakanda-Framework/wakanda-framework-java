/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

  private String tenantToken;

  private Long createdBy;

  private DateTime createdOn;

  private Long lastUpdatedBy;

  private DateTime lastUpdatedOn;

  private Boolean isActive;
}
