/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wakanda.framework.entity.BaseEntity;

/**
 * @author - adityakumar
 * @date - 19/05/2022
 * @since - 1.0.0
 * @param <T> - Entity of type of BaseEntity.
 * @param <ID> - Datatype of id field of that entity.
 */
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
    extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {}
