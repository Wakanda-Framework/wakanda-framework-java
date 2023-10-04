/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.HttpMethod;

/**
 * Param field not null validate.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullField {

  String message() default "{javax.validation.constraints.NotNull.message}";

  HttpMethod[] method() default HttpMethod.GET; // For resource access.
}
