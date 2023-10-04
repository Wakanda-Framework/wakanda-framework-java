/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wakanda.framework.constants.SessionConstant;
import org.wakanda.framework.model.UserPrincipal;
import org.wakanda.framework.tools.BaseStaticValues;

/**
 * @author - adityakumar
 * @date - 19/05/2022
 * @since - 1.0.0
 * @implNote - BaseEntity is an abstract entity which consists of the common fields across all the
 *     other entities.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

  /**
   * @serialVersionUID - The serialVersionUID attribute is an identifier that is used to
   *     serialize/deserialize an object of a Serializable class.
   */
  @Transient public static final long serialVersionUID = BaseStaticValues.serialVersionUID;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ID id;

  @Column(name = "tenant_token")
  @NotBlank(message = "Tenant Token cannot be null/empty.")
  private String tenantToken;

  @Column(name = "created_by")
  @NotNull(message = "Created by cannot be null")
  private Long createdBy;

  @Column(name = "created_on")
  @NotNull(message = "Created on cannot be null")
  @Temporal(TemporalType.TIMESTAMP)
  @Type(type = "org.wakanda.framework.util.TimeBridgeForMillis")
  private DateTime createdOn;

  @Column(name = "last_updated_by")
  @NotNull(message = "Last updated cannot be null")
  private Long lastUpdatedBy;

  @Column(name = "last_updated_on")
  @NotNull(message = "Last updated cannot be null")
  @Temporal(value = TemporalType.TIMESTAMP)
  @Type(type = "org.wakanda.framework.util.TimeBridgeForMillis")
  private DateTime lastUpdatedOn;

  @Version
  @Column(name = "version")
  @NotNull(message = "Version cannot be null")
  private Long version;

  @Column(name = "is_active")
  @NotNull(message = "isActive cannot be null")
  private Boolean isActive;

  @PrePersist
  protected void onCreate() {
    this.createdOn = this.lastUpdatedOn = DateTime.now();
    this.createdBy = this.lastUpdatedBy = getSessionUserId();
  }

  @PreUpdate
  @Transactional(propagation = Propagation.REQUIRED)
  protected void onUpdate() {
    this.lastUpdatedOn = DateTime.now();
    final Long currentUser = getSessionUserId();
    if (currentUser != null) {
      this.lastUpdatedBy = currentUser;
    }
  }

  private Long getSessionUserId() {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      log.error("Context Auth Missing. ");
      return SessionConstant.RARA_ADMIN_ID;
    }
    final UserPrincipal userPrincipal =
        (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return Long.parseLong(userPrincipal.getUser().getUserId());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    BaseEntity<ID> that = (BaseEntity<ID>) o;
    return tenantToken != null && Objects.equals(tenantToken, that.tenantToken);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
