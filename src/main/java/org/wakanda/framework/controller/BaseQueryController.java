/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wakanda.framework.dto.specification.SearchRequest;
import org.wakanda.framework.entity.BaseEntity;
import org.wakanda.framework.response.dto.ResponseDTO;
import org.wakanda.framework.response.enums.ResponseType;
import org.wakanda.framework.response.helper.ResponseHelper;
import org.wakanda.framework.service.BaseService;

/**
 * @author - adityakumar
 * @date - 19/05/2022
 * @since - 1.0.0
 * @param <T> - Entity of type of BaseEntity.
 * @param <ID> - Datatype of id field of that entity.
 */
@Component
public class BaseQueryController<T extends BaseEntity<ID>, ID extends Serializable>
    extends BaseOperationsController<T, ID> {
  private final BaseService<T, ID> baseService;

  private final ResponseHelper<T> responseHelper;

  @Autowired
  public BaseQueryController(BaseService<T, ID> baseService, ResponseHelper<T> responseHelper) {
    super(baseService, responseHelper);
    this.baseService = baseService;
    this.responseHelper = responseHelper;
  }

  @GetMapping("/get")
  @Operation(summary = "Get all objects")
  public ResponseEntity<ResponseDTO<List<T>>> all() {
    return responseHelper.ok(baseService.findAll());
  }

  @PostMapping("/search")
  @Operation(
      summary =
          "Get all objects based on filter and sort request. Returns all objects if no sort and filter criteria is passed. ")
  public ResponseEntity<ResponseDTO<Page<T>>> page(@RequestBody SearchRequest searchRequest) {
    return responseHelper.ok(baseService.findAll(searchRequest));
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<ResponseDTO<T>> getById(@PathVariable ID id) {
    Optional<T> optional = baseService.findById(id);
    return optional
        .map(responseHelper::ok)
        .orElseGet(
            () ->
                responseHelper.error(
                    HttpStatus.NOT_FOUND, ResponseType.NOT_FOUND, "Entity not found"));
  }
}
