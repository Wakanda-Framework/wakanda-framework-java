/* (C)2022 */
package org.wakanda.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wakanda.framework.dto.specification.SearchRequest;
import org.wakanda.framework.dto.specification.SearchSpecification;
import org.wakanda.framework.entity.BaseEntity;
import org.wakanda.framework.enums.ErrorType;
import org.wakanda.framework.exception.type.BaseException;
import org.wakanda.framework.repository.BaseRepository;

@Service
@Transactional
@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
    implements BaseService<T, ID> {

  private final BaseRepository<T, ID> baseRepository;

  @Autowired
  protected BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
    this.baseRepository = baseRepository;
  }

  @Override
  public T save(T entity) {
    return baseRepository.save(entity);
  }

  @Override
  public T update(T entity) {
    return baseRepository
        .findById(entity.getId())
        .map(savedEntity -> baseRepository.save(entity))
        .orElseThrow(() -> new BaseException(ErrorType.NO_SUCH_ELEMENT_ERROR));
  }

  @Override
  public List<T> findAll() {
    return baseRepository.findAll();
  }

  @Override
  public Page<T> findAll(SearchRequest searchRequest) {
    SearchSpecification<T> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable =
        SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
    return baseRepository.findAll(specification, pageable);
  }

  @Override
  public Optional<T> findById(ID entityId) {
    return baseRepository.findById(entityId);
  }

  @Override
  public T softDeleteById(ID entityId) {
    T alreadyPresentEntity =
        this.findById(entityId)
            .orElseThrow(
                () ->
                    new RuntimeException("No entity present corresponding to given" + " entityId"));
    alreadyPresentEntity.setIsActive(Boolean.FALSE);
    return this.save(alreadyPresentEntity);
  }

  @Override
  public void deleteById(ID entityId) {
    this.baseRepository.deleteById(entityId);
  }
}
