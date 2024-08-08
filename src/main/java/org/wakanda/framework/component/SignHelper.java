/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.wakanda.framework.constant.SignatureConstant;
import org.wakanda.framework.exception.IllegalParamException;
import org.wakanda.framework.exception.SignFailedException;
import org.wakanda.framework.param.BaseParam;

/**
 * Sign helper.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Component
public class SignHelper {

  /**
   * Check the sign.
   *
   * @param publicKey public key
   * @return success or not
   */
  public boolean signCheck(String publicKey, BaseParam params)
      throws UnsupportedEncodingException, SignFailedException, IllegalParamException {
    // Prepare to validateWithOutSignCheck signature.
    if (StringUtils.isEmpty(params.getSign())) {
      throw new IllegalParamException("sign cannot be null");
    }
    // Transform encode.
    params.setSign(
        URLDecoder.decode(
            new String(Base64.decodeBase64(params.getSign())), SignatureConstant.CHARSET_UTF8));
    // Signature
    return params.isSignValid(publicKey);
  }
}
