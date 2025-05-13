/* (C) 2025 WAKANDA FRAMEWORK */
package org.wakanda.framework.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.wakanda.framework.entity.BaseEntity;

@Slf4j
public class WakandaUtils {

  private static <T> Predicate<PropertyDescriptor> nulls(T obj) {
    return pd -> {
      Method getterMethod = pd.getReadMethod();
      try {
        return (getterMethod != null && getterMethod.invoke(obj) == null);
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
        return false;
      }
    };
  }

  @SuppressWarnings("unused")
private static <T> Predicate<PropertyDescriptor> notNulls(T obj) {
    return pd -> {
      Method getterMethod = pd.getReadMethod();
      try {
        return (getterMethod != null && getterMethod.invoke(obj) != null);
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
        return false;
      }
    };
  }

  public static <T> boolean areAllEntityVariablesNull(T obj) {
    log.trace("Enitity for Null Check = {} ", obj.toString());
    try {
      PropertyDescriptor[] propDescArr =
          Introspector.getBeanInfo(obj.getClass(), BaseEntity.class).getPropertyDescriptors();

      List<String> ms =
          Arrays.stream(propDescArr)
              .filter(nulls(obj))
              .map(PropertyDescriptor::getName)
              .collect(Collectors.toList());

      int nullMethodSize = ms.size();

      Field[] f = obj.getClass().getDeclaredFields();

      int fieldSize =
          (int)
              Arrays.stream(f)
                  .filter(
                      x -> {
                        if ("log".equals(x.getName()) || "serialVersionUID".equals(x.getName()))
                          return false;
                        else return true;
                      })
                  .count();

      log.trace("Null value getter: {} ", nullMethodSize);
      log.trace("Field array size: {} \n", fieldSize);

      return nullMethodSize == fieldSize;

    } catch (IntrospectionException e) {
      log.error("Error while accessing object: " + e.getMessage());
    }
    return false;
  }
}
