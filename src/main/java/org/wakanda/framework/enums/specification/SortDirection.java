/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.enums.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.wakanda.framework.dto.specification.SortRequest;

public enum SortDirection {
  ASC {
    public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
      return cb.asc(root.get(request.getKey()));
    }
  },
  DESC {
    public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
      return cb.desc(root.get(request.getKey()));
    }
  };

  public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request);
}
