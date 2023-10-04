/* (C)2022 */
package org.wakanda.framework.tools;

import org.apache.tomcat.util.codec.binary.Base64;
import org.wakanda.framework.constant.CommonsConstant;
import org.wakanda.framework.exception.type.IllegalExtTypeException;

/**
 * decode base64 image utilities.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
public class Base64ImageHelper {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  private Base64ImageHelper() {}

  /**
   * Transform Mr.Base64Image to Headless Knight.
   *
   * @param image Mr.Base64Image
   * @return Headless Knight
   * @throws IllegalExtTypeException not in the list
   */
  public static byte[] transformToHeadlessKnight(String image) throws IllegalExtTypeException {
    return Base64.decodeBase64(cutHead(image));
  }

  private static final String HAIR = "data:image/";
  private static final String NECK = ";base64,";
  private static final String PNG = "png";
  private static final String JPEG = "jpeg";
  private static final String JPG = "jpg";
  private static final String GIF = "gif";

  // ------------------------
  // PRIVATE METHODS
  // ------------------------

  /**
   * Cut head! Mr.Base64Image to Mr.headless.
   *
   * @param image Mr.Base64Image
   * @return Mr.Headless
   * @throws IllegalExtTypeException not in the list
   */
  private static String cutHead(String image) throws IllegalExtTypeException {
    return image.replace(formHead(HAIR, cutWhom(image), NECK), "");
  }

  /**
   * I'll Form the Head!
   *
   * @param hair hair
   * @param face face
   * @param neck neck
   * @return head
   */
  private static String formHead(String hair, String face, String neck) {
    return String.join(CommonsConstant.BLANK, hair, face, neck);
  }

  /**
   * Who‘s the poor guy, let me check the list.
   *
   * @param image luckless guy
   * @return name of luckless guy
   * @throws IllegalExtTypeException not in the list
   */
  private static String cutWhom(String image) throws IllegalExtTypeException {
    if (image.contains(PNG)) {
      return PNG;
    } else if (image.contains(JPEG)) {
      return JPEG;
    } else if (image.contains(JPG)) {
      return JPG;
    } else if (image.contains(GIF)) {
      return GIF;
    } else {
      throw new IllegalExtTypeException("Illegal picture ext.");
    }
  }
}
