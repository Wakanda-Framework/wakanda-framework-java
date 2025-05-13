/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.tools;

import java.util.Random;

/**
 * Utility to generate OTP
 *
 * @author vipulmeehnia
 * @date 2021-09-15
 * @since JDK1.8
 */
public class OtpGenerater {

  /**
   * Generates a new random OTP for the given size.
   *
   * @param digits
   * @return
   */
  public static String generateOTP(int digits) {
    String contain = "0123456789";
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder(digits);
    for (int i = 0; i < digits; i++) {
      sb.append(contain.charAt(rnd.nextInt(contain.length())));
    }
    return sb.toString();
  }
}
