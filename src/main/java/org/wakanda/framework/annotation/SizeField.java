/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.springframework.http.HttpMethod;

/**
 * Param field size validate.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface SizeField {

  String message() default "{javax.validation.constraints.Size.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * @return size the element must be higher or equal to
   */
  int min() default 0;

  /**
   * @return size the element must be lower or equal to
   */
  int max() default Integer.MAX_VALUE;

  /**
   * Defines several {@link javax.validation.constraints.Size} annotations on the same element.
   *
   * @see javax.validation.constraints.Size
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  @interface List {

    javax.validation.constraints.Size[] value();
  }

  HttpMethod[] method() default HttpMethod.GET; // For resource access.
}
