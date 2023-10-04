/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.dto.specification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wakanda.framework.enums.specification.FieldType;
import org.wakanda.framework.enums.specification.Operator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private String key;

  private Operator operator;

  private FieldType fieldType;

  private transient Object value;

  private transient Object valueTo;

  private transient List<Object> values;
}
