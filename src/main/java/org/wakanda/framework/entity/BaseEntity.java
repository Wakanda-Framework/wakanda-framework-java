/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wakanda.framework.constants.SessionConstant;
import org.wakanda.framework.model.UserPrincipal;
import org.wakanda.framework.util.WakandaUtils;

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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Slf4j
@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {

  @Transient private static final long serialVersionUID = -3253056241076756435L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ID id;

  @Column(name = "created_by", nullable = false)
  private Long createdBy;

  @Column(name = "created_on", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private DateTime createdOn;

  @Column(name = "last_updated_by")
  private Long lastUpdatedBy;

  @Column(name = "last_updated_on")
  @Temporal(value = TemporalType.TIMESTAMP)
  private DateTime lastUpdatedOn;

  @Version
  @Column(name = "version")
  private int version;

  @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
  private Boolean isActive;

  @PrePersist
  protected void onCreate() {
    this.isActive = true;
    this.createdOn = DateTime.now();
    this.createdBy = this.createdBy != null ? this.createdBy : getSessionUserId();
  }

  @PreUpdate
  @Transactional(propagation = Propagation.REQUIRED)
  protected void onUpdate() {
    this.lastUpdatedOn = DateTime.now();
    final Long currentUser = getSessionUserId();
    if (currentUser != null && this.lastUpdatedBy == null) {
      this.lastUpdatedBy = currentUser;
    }
    this.version = this.version + 1;
  }

  private Long getSessionUserId() {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      log.error("Context Auth Missing. ");
      return SessionConstant.WAKANDA_ADMIN_ID;
    }
    final UserPrincipal userPrincipal =
        (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return Long.parseLong(userPrincipal.getUser().getUserId());
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object other) {
    if (null == other) return false;
    if (WakandaUtils.areAllEntityVariablesNull(this)) return false;
    if (WakandaUtils.areAllEntityVariablesNull(other)) return false;
    if (this == other) return true;
    if (!(other instanceof BaseEntity)) return false;
    if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false;

    BaseEntity<ID> baseEntity = (BaseEntity<ID>) other;
    log.info("other = {}", other);
    // log.info("this = {}", this);
    if (!baseEntity.getCreatedOn().equals(getCreatedOn())) return false;
    if (null != baseEntity.getLastUpdatedOn() && null != getLastUpdatedOn())
      if (!baseEntity.getLastUpdatedOn().equals(getLastUpdatedOn()))
        return false; // TODO: fix this line
    if (baseEntity.getVersion() != getVersion()) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    result = null != getCreatedOn() ? getCreatedOn().hashCode() : 1;
    result =
        getVersion() * result + (null != getLastUpdatedOn() ? getLastUpdatedOn().hashCode() : 2);
    return result;
  }
}
