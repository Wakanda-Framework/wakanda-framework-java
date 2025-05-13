/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wakanda.framework.dto.specification.SearchRequest;
import org.wakanda.framework.dto.specification.SearchSpecification;
import org.wakanda.framework.entity.BaseEntity;
import org.wakanda.framework.enums.ErrorType;
import org.wakanda.framework.exception.BaseException;
import org.wakanda.framework.exception.PostProcessingException;
import org.wakanda.framework.exception.PreProcessingException;
import org.wakanda.framework.repository.BaseRepository;

@Slf4j
public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
    implements BaseService<T, ID> {

  private final BaseRepository<T, ID> baseRepository;

  protected BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
    this.baseRepository = baseRepository;
  }

  @Override
  public T preSave(T entity) throws PreProcessingException {
    if (null == entity) {
      throw new PreProcessingException(ErrorType.EMPTY_ENTITY);
    } else return entity;
  }

  @Override
  public T save(T entity) throws BaseException {
    T e1 = preSave(entity);
    baseRepository.save(e1);
    T e2 = postSave(entity, e1);
    return e2;
  }

  @Override
  public T postSave(T oldEntity, T newEntity) throws PostProcessingException {
    return newEntity;
  }

  @Override
  public List<T> findAll() throws BaseException {
    List<T> result = baseRepository.findAll();
    if (null == result || result.isEmpty()) {
      throw new BaseException(ErrorType.NO_DATA_FOUND);
    } else {
      return result;
    }
  }

  @Override
  public Page<T> findAll(SearchRequest searchRequest) throws BaseException {
    SearchSpecification<T> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable =
        SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
    Page<T> result = baseRepository.findAll(specification, pageable);
    if (null == result || result.isEmpty()) {
      throw new BaseException(ErrorType.NO_DATA_FOUND);
    } else {
      return result;
    }
  }

  @Override
  public Optional<T> findById(ID entityId) throws BaseException {
    T alreadyPresentEntity =
        baseRepository
            .findById(entityId)
            .orElseThrow(() -> new BaseException(ErrorType.NO_DATA_FOUND));
    return Optional.of(alreadyPresentEntity);
  }

  @Override
  public T preUpdate(T entity, ID entityId) throws PreProcessingException {
    if (null == entity) {
      throw new PreProcessingException(ErrorType.EMPTY_ENTITY);
    } else if (null == entityId || entity.getId() != entityId) {
      throw new PreProcessingException(
          "The identifier for the given entity doesn't match or is null/empty.");
    }

    T e1 = baseRepository.findById(entityId).get();
    if (!e1.equals(entity)) {
      throw new PreProcessingException(
          "The record to be updated doesn't match the record with the given ID.");
    }

    return entity;
  }

  @Override
  public T update(T entity, ID entityId) throws BaseException {
    T e1 = preUpdate(entity, entityId);
    T e2 =
        postUpdate(
            entity,
            baseRepository
                .findById(entity.getId())
                .map(saveEntity -> baseRepository.save(e1))
                .orElseThrow(() -> new BaseException(ErrorType.NO_SUCH_ELEMENT_ERROR)));
    return e2;
  }

  @Override
  public T postUpdate(T oldEntity, T newEntity) throws PostProcessingException {
    return newEntity;
  }

  @Override
  public T preDelete(ID entityId) throws PreProcessingException {
    if (null == entityId) {
      throw new PreProcessingException(
          "The identifier for the given entity doesn't match or is null/empty.");
    }
    return this.findById(entityId)
        .orElseThrow(() -> new PreProcessingException(ErrorType.NO_DATA_FOUND));
  }

  @Override
  public T delete(ID entityId) throws BaseException {
    T alreadyPresentEntity = preDelete(entityId);
    alreadyPresentEntity.setIsActive(Boolean.FALSE);
    return postDelete(this.update(alreadyPresentEntity, entityId));
  }

  @Override
  public void deleteById(ID entityId) throws BaseException {
    baseRepository.deleteById(entityId);
  }

  @Override
  public T postDelete(T entity) throws PostProcessingException {
    return entity;
  }
}
