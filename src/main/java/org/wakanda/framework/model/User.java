/* (C)2022 */
package org.wakanda.framework.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private String userId;

  private String name;

  private String email;

  private List<String> roles;

  //    private List<String> modules;
  //
  //    private List<String> businesses;
  //
  //    private List<String> businessAccounts;

}
