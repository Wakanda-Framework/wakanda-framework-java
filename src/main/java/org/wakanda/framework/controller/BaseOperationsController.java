/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class BaseOperationsController<T extends BaseEntity<ID>, ID extends Serializable> {

  private final BaseService<T, ID> baseService;
  private final ResponseHelper<T> responseHelper;

  public BaseOperationsController(
      BaseService<T, ID> baseService, ResponseHelper<T> responseHelper) {
    this.baseService = baseService;
    this.responseHelper = responseHelper;
  }

  @PostMapping("/create")
  @Operation(summary = "Create new object")
  public ResponseEntity<ResponseDTO<T>> create(@RequestBody @Valid T entity) {
    return responseHelper.ok(baseService.save(entity));
  }

  @PutMapping("/update")
  @Operation(summary = "Update existing object")
  public ResponseEntity<ResponseDTO<T>> update(@RequestBody @Valid T entity) {
    return responseHelper.ok(baseService.update(entity));
  }

  @DeleteMapping("/soft-delete/{id}")
  @Operation(summary = "Soft delete object by ID. Entity will be marked inactive and kept in DB.")
  public ResponseEntity<ResponseDTO<T>> softDeleteById(@PathVariable ID id) {
    T result = baseService.softDeleteById(id);
    if (Objects.isNull(result)) {
      return responseHelper.error(HttpStatus.NOT_FOUND, ResponseType.NOT_FOUND);
    }
    return responseHelper.ok(result);
  }

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete object by ID.")
  public ResponseEntity<ResponseDTO<T>> deleteById(@PathVariable ID id) {
    baseService.deleteById(id);
    return responseHelper.ok("Entity deleted successfully");
  }
}
