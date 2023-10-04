/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Converter
public class JsonToGenericObjectConverter implements AttributeConverter<Object, String> {

  @Autowired private ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Object o) {
    String objectsJson = null;
    try {
      objectsJson = objectMapper.writeValueAsString(o);
    } catch (final JsonProcessingException e) {
      log.error("JSON writing error", e);
    }

    return objectsJson;
  }

  @Override
  public Object convertToEntityAttribute(String jsonString) {
    if (Objects.isNull(jsonString)) return Collections.emptyList();
    Object object = null;
    try {
      object = objectMapper.readValue(jsonString, Object.class);
    } catch (final IOException e) {
      log.error("JSON reading error", e);
    }

    return object;
  }
}
