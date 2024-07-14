package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(
        name = "Delivery Employee [DeliveryEmployeeController]",
        description = "Controlador que fornece os serviços de criação,consulta,alteracao e exclusao de empregados."
)
public interface DeliveryEmployeeControllerImpl {

    @Operation(summary = "Criar um empregado.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Empregado criado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryEmployee.class))
                    })
    })
    ResponseEntity<DeliveryEmployee> createEmployee(
            @Parameter(hidden = true) DeliveryEmployee deliveryEmployee
    );

    @Operation(summary = "Buscar um empregado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empregado encontrado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryEmployee.class))
                    })
    })
    ResponseEntity<DeliveryEmployee> findEmployee(@Parameter(description = "Id do empregado a ser consultado.") Long orderId);

    @Operation(summary = "Atualiza um empregado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empregado atualizado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryEmployee.class))
                    })
    })
    ResponseEntity<DeliveryEmployee> alterEmployee(
            @Parameter(description = "Id do empregado a ser consultado.") Long orderId,
            @Parameter(hidden = true) UriComponentsBuilder uriComponentsBuilder);

    @Operation(summary = "Deleta um empregado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empregado apagado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryEmployee.class))
                    })
    })
    ResponseEntity<DeliveryEmployee> alterEmployee(@Parameter(description = "Id do empregado a ser consultado.") Long orderId);

}
