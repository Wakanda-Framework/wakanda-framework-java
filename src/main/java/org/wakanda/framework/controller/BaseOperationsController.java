/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wakanda.framework.entity.BaseEntity;
import org.wakanda.framework.exception.handler.response.BaseExceptionResponse;
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
public class BaseOperationsController<T extends BaseEntity<ID>, ID extends Serializable> {

  private final BaseService<T, ID> baseService;
  private final ResponseHelper<T> responseHelper;

  public BaseOperationsController(
      BaseService<T, ID> baseService, ResponseHelper<T> responseHelper) {
    this.baseService = baseService;
    this.responseHelper = responseHelper;
  }

  @PostMapping(produces = "application/org.wakanda.fw-v1+json")
  @Operation(summary = "createObject", description = "Create new object for this api")
  @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Creation successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "406",
        description = "Content Type not accepted. Please refer to the documentation.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        }),
    @ApiResponse(
        responseCode = "422",
        description = "Unprocessable object. Please refer to the payload for details.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        })
  })
  public ResponseEntity<ResponseDTO<T>> create(@RequestBody @Valid T entity) {
    return responseHelper.success(
        HttpStatus.CREATED, ResponseType.CREATED, baseService.save(entity));
  }

  @PutMapping(path = "{id}", produces = "application/org.wakanda.fw-v1+json")
  @Operation(summary = "updateObject", description = "Update existing object for this api")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Updated successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "406",
        description = "Content Type not accepted. Please refer to the documentation.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        }),
    @ApiResponse(
        responseCode = "422",
        description = "Unprocessable object. Please refer to the payload for details.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        })
  })
  public ResponseEntity<ResponseDTO<T>> update(
      @RequestBody @Valid T entity,
      @PathVariable @Parameter(name = "Object Id", example = "1") ID id) {
    return responseHelper.ok(baseService.update(entity, id));
  }

  @DeleteMapping("{id}")
  @Operation(
      summary = "deleteObject",
      description = "Soft delete object by ID. Entity will be marked inactive and kept in DB.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Deleted successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "406",
        description = "Content Type not accepted. Please refer to the documentation.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        }),
    @ApiResponse(
        responseCode = "422",
        description = "Unprocessable object. Please refer to the payload for details.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
        })
  })
  public ResponseEntity<ResponseDTO<T>> softDeleteById(
      @PathVariable @Parameter(name = "Object Id", example = "1") ID id) {
    T result = baseService.delete(id);
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
