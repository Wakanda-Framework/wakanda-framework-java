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

/**
 * Json to Iterable converter for attribute type variation.
 *
 * @author prajwalshenoy
 * @since May 2022
 */
@Slf4j
@Converter
public class JsonToIterableConverter implements AttributeConverter<Iterable<?>, String> {
  @Autowired private ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Iterable<?> objects) {
    String objectsJson = null;
    try {
      objectsJson = objectMapper.writeValueAsString(objects);
    } catch (final JsonProcessingException e) {
      log.error("JSON writing error", e);
    }

    return objectsJson;
  }

  @Override
  public Iterable<?> convertToEntityAttribute(String jsonString) {
    if (Objects.isNull(jsonString)) return Collections.emptyList();
    Iterable<?> iterableList = null;
    try {
      iterableList = objectMapper.readValue(jsonString, Iterable.class);
    } catch (final IOException e) {
      log.error("JSON reading error", e);
    }

    return iterableList;
  }
}
