/* (C)2022 */
package org.wakanda.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

  private User user;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    this.user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
    //        this.user
    //                .getModules()
    //                .forEach(module -> authorities.add(new SimpleGrantedAuthority(module)));
    //        this.user
    //                .getBusinesses()
    //                .forEach(business -> authorities.add(new SimpleGrantedAuthority(business)));
    //        this.user
    //                .getBusinessAccounts()
    //                .forEach(businessAccounts -> authorities.add(new
    // SimpleGrantedAuthority(businessAccounts)));
    return authorities;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
