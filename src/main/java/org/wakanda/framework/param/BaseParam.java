/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Transient;
import lombok.Data;
import org.wakanda.framework.annotation.SignField;
import org.wakanda.framework.constant.SignatureConstant;
import org.wakanda.framework.exception.SignFailedException;
import org.wakanda.framework.tools.BaseStaticValues;
import org.wakanda.framework.tools.SignatureUtils;

/**
 * Base param.
 *
 * @author Vipul Meehnia
 * @date 8/16/21
 * @since JDK1.8
 */
@Data
public class BaseParam implements Serializable {

  private static final Map<String, Object> baseFields = new HashMap<>();

  private static final String EQUAL = "=";

  @Serial private static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @Transient
  @JsonIgnore
  private Integer pageNo = 1;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @Transient
  @JsonIgnore
  private Integer pageSize = 20;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @Transient
  @JsonIgnore
  private String sortBy;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @Transient
  @JsonIgnore
  private String sign;

  @JsonIgnore
  public Map<String, Object> getBaseFields() {
    baseFields.put("pageNo", pageNo);
    baseFields.put("pageSize", pageSize);
    baseFields.put("sortBy", sortBy);
    baseFields.put("sign", sign);
    return baseFields;
  }

  public boolean isSignValid(String publicKey) throws SignFailedException {
    String content = getSignContent();
    return SignatureUtils.rsaCheckContent(
        content, this.getSign(), publicKey, SignatureConstant.CHARSET_UTF8);
  }

  /**
   * Signature.
   *
   * @param privateKey Local private key.
   */
  public void sign(String privateKey) throws SignFailedException {
    String content = getSignContent(); // JsonConverter.convertToJSON(this).toString();
    this.sign = SignatureUtils.rsaSign(content, privateKey, SignatureConstant.CHARSET_UTF8);
  }

  /**
   * Get the signature content.
   *
   * @return signature content
   */
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @JsonIgnore
  public String getSignContent() throws SignFailedException {
    StringBuffer buffer = new StringBuffer();
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
      PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      Arrays.sort(pds, Comparator.comparing(FeatureDescriptor::getName));
      for (PropertyDescriptor pd : pds) {
        Method method = pd.getReadMethod();
        if (method == null) { // Ignore read-only field
          continue;
        }
        Field field = null;
        String itemName = pd.getName();
        try {
          field =
              getBaseFields().containsKey(itemName)
                  ? BaseParam.class.getDeclaredField(itemName)
                  : this.getClass().getDeclaredField(itemName);
        } catch (Exception ignored) {
        }
        if (field == null || !field.isAnnotationPresent(SignField.class)) {
          continue; // Ignore field without ParamField annotation.
        }
        field.setAccessible(true);
        Object itemValue = field.get(this);
        if (itemValue == null) {
          continue;
        }
        buffer.append(itemName).append(EQUAL);
        if (itemValue.getClass().isAssignableFrom(List.class)) {
          List<?> list = (List<?>) itemValue;
          list.forEach(buffer::append);
        } else {
          buffer.append(itemValue);
        }
      }
      return buffer.toString();
    } catch (Exception e) {
      throw new SignFailedException();
    }
  }
}
