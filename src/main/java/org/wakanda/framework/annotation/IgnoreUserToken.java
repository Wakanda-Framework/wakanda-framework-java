/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ignore user token authorization.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface IgnoreUserToken {}
