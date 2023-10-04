/* (C)2022 */
package org.wakanda.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.wakanda.framework.dto.specification.SearchRequest;
import org.wakanda.framework.entity.BaseEntity;

/**
 * @author - adityakumar
 * @date - 19/05/2022
 * @since - 1.0.0
 * @param <T> - Entity of type of BaseEntity.
 * @param <ID> - Datatype of id field of that entity.
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
  T save(T entity);

  T update(T entity);

  List<T> findAll();

  Page<T> findAll(SearchRequest searchRequest);

  Optional<T> findById(ID entityId);

  T softDeleteById(ID entityId);

  void deleteById(ID entityId);
}
