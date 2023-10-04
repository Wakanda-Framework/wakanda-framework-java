/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums.specification;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Slf4j
public enum FieldType {
  BOOLEAN {
    public Object parse(String value) {
      return Boolean.valueOf(value);
    }
  },

  CHAR {
    public Object parse(String value) {
      return value.charAt(0);
    }
  },

  DATE {
    public Object parse(String value) {
      Object date = null;
      try {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        date = LocalDateTime.parse(value, formatter);
      } catch (Exception e) {
        log.info("Failed parse field type DATE {}", e.getMessage());
      }

      return date;
    }
  },

  DOUBLE {
    public Object parse(String value) {
      return Double.valueOf(value);
    }
  },

  INTEGER {
    public Object parse(String value) {
      return Integer.valueOf(value);
    }
  },

  LONG {
    public Object parse(String value) {
      return Long.valueOf(value);
    }
  },

  STRING {
    public Object parse(String value) {
      return value;
    }
  };

  public abstract Object parse(String value);
}
