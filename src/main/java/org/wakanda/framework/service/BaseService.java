/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.wakanda.framework.dto.specification.SearchRequest;
import org.wakanda.framework.entity.BaseEntity;
import org.wakanda.framework.exception.BaseException;
import org.wakanda.framework.exception.PostProcessingException;
import org.wakanda.framework.exception.PreProcessingException;

/**
 * @author - adityakumar
 * @date - 19/05/2022
 * @since - 1.0.0
 * @param <T> - Entity of type of BaseEntity.
 * @param <ID> - Datatype of id field of that entity.
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

  T preSave(T entity) throws PreProcessingException;

  T save(T entity) throws BaseException;

  T postSave(T oldEntity, T newEntity) throws PostProcessingException;

  List<T> findAll() throws BaseException;

  Page<T> findAll(SearchRequest searchRequest) throws BaseException;

  Optional<T> findById(ID entityId) throws BaseException;

  T preUpdate(T entity, ID entityId) throws PreProcessingException;

  T update(T entity, ID entityId) throws BaseException;

  T postUpdate(T oldEntity, T newEntity) throws PostProcessingException;

  T preDelete(ID entityId) throws PreProcessingException;

  T delete(ID entityId) throws BaseException;

  T postDelete(T entity) throws PostProcessingException;

  void deleteById(ID entityId) throws BaseException;
}
