package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
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
        name = "Entregador [DelivererController]",
        description = "Controlador para gerenciamento de entregadores."
)
public interface DelivererController {

    @Operation(summary = "Criar um entregador.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entregador criado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Deliverer.class))
                    })
    })
    ResponseEntity create(
            @Parameter(description = "Atributos do entregador a ser criado.") DelivererCreationRequestDTO delivererDTO,
            @Parameter(hidden = true) UriComponentsBuilder uriComponentsBuilder
    );

    @Operation(summary = "Buscar um entregador pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entregador encontrado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Deliverer.class))
                    })
    })
    ResponseEntity<?> findById(@Parameter(description = "Id do entregador a ser consultado.") Long orderId);

    @Operation(summary = "Atualizar um entregador pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entregador atualizado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Deliverer.class))
                    })
    })
    ResponseEntity<?> alterEmployee(
            @Parameter(description = "Id do entregador a ser consultado.") Long orderId,
            @Parameter(description = "Atributos a serem alterados do entregador.") Deliverer deliverer);

    @Operation(summary = "Deletar um entregador pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entregador deletado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Deliverer.class))
                    })
    })
    ResponseEntity<?> deleteEmployee(@Parameter(description = "Id do entregador a ser deletado.") Long orderId);

}
