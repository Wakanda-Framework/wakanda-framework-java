/* (C) 2022 WAKANDA FRAMEWORK */
package org.wakanda.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wakanda.framework.dto.specification.SearchRequest;
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
public class BaseQueryController<T extends BaseEntity<ID>, ID extends Serializable>
    extends BaseOperationsController<T, ID> {
  private final BaseService<T, ID> baseService;

  private final ResponseHelper<T> responseHelper;

  public BaseQueryController(BaseService<T, ID> baseService, ResponseHelper<T> responseHelper) {
    super(baseService, responseHelper);
    this.baseService = baseService;
    this.responseHelper = responseHelper;
  }

  @GetMapping
  @Operation(summary = "getAllObject", description = "Get all the available objects.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Fetch successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "404",
        description = "No objects found.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
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
  public ResponseEntity<ResponseDTO<List<T>>> all() {
    return responseHelper.ok(baseService.findAll());
  }

  @PostMapping("/search")
  @Operation(
      summary = "search",
      description =
          "Get all objects based on filter and sort request. Returns all objects if no sort and filter criteria is passed.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Fetch successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "404",
        description = "No objects found.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
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
  public ResponseEntity<ResponseDTO<Page<T>>> search(@RequestBody SearchRequest searchRequest) {
    return responseHelper.ok(baseService.findAll(searchRequest));
  }

  @GetMapping("{id}")
  @Operation(summary = "getById", description = "Get object based on its ID.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Fetch successful",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class))
        }),
    @ApiResponse(
        responseCode = "404",
        description = "No objects found.",
        content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BaseExceptionResponse.class))
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
  public ResponseEntity<ResponseDTO<T>> getById(
      @PathVariable @Parameter(name = "Object Id", example = "1") ID id) {
    Optional<T> optional = baseService.findById(id);
    return optional
        .map(responseHelper::ok)
        .orElseGet(
            () ->
                responseHelper.error(
                    HttpStatus.NOT_FOUND, ResponseType.NOT_FOUND, "Entity not found"));
  }
}
