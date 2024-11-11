/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.entity;

import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wakanda.framework.constants.SessionConstant;
import org.wakanda.framework.model.UserPrincipal;

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
  @Type(type = "org.wakanda.framework.util.TimeBridgeForMillis")
  private DateTime createdOn;

  @Column(name = "last_updated_by")
  private Long lastUpdatedBy;

  @Column(name = "last_updated_on")
  @Temporal(value = TemporalType.TIMESTAMP)
  @Type(type = "org.wakanda.framework.util.TimeBridgeForMillis")
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
    if (this == other) return true;
    if (!(other instanceof BaseEntity)) return false;
    if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false;

    BaseEntity<ID> baseEntity = (BaseEntity<ID>) other;

    if (!baseEntity.getCreatedOn().equals(getCreatedOn())) return false;
    if (null != baseEntity.getLastUpdatedOn() && null != getLastUpdatedOn())
    	if (!baseEntity.getLastUpdatedOn().equals(getLastUpdatedOn())) return false;
    if (baseEntity.getVersion() != getVersion()) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    result = getCreatedOn().hashCode();
    result = getVersion() * result + (null != getLastUpdatedOn() ? getLastUpdatedOn().hashCode() : 0);
    return result;
  }
}
