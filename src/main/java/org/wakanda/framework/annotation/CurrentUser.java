/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * Get current user.{@link AuthenticationPrincipal}
 *
 * <pre>
 * @Controller
 * public class MyController {
 *   @RequestMapping("/user/current/show")
 *   public String show(@CurrentUser CustomUser customUser) {
 *       // do something with CustomUser
 *       return "view";
 *   }
 * </pre>
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface CurrentUser {}
