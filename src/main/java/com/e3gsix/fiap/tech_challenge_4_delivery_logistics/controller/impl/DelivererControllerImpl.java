package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.DelivererController;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DelivererService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl.DelivererControllerImpl.URL_DELIVERER;

@RestController
@RequestMapping(URL_DELIVERER)
@RequiredArgsConstructor
public class DelivererControllerImpl implements DelivererController {

    public static final String URL_DELIVERER = "/deliverer";
    public static final String URL_DELIVERER_ID = "/{delivererId}";

    private final DelivererService delivererService;

    @PostMapping
    @Override
    public ResponseEntity create(
            @RequestBody DelivererCreationRequestDTO delivererDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long idCreatedDeliverer = this.delivererService.create(delivererDTO);

        URI uri = uriComponentsBuilder.path(URL_DELIVERER + URL_DELIVERER_ID)
                .buildAndExpand(idCreatedDeliverer)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(URL_DELIVERER_ID)
    public ResponseEntity<?> findById(@PathVariable Long delivererId) {
        try {
            Deliverer employee = delivererService.findById(delivererId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(URL_DELIVERER_ID)
    public ResponseEntity<?> alterEmployee(@PathVariable Long delivererId, @RequestBody Deliverer deliverer) {
        try {
            Deliverer employee = delivererService.alterEmployee(delivererId, deliverer);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(URL_DELIVERER_ID)
    public ResponseEntity<?> deleteEmployee(@PathVariable Long delivererId) {
        try {
            delivererService.deleteEmployee(delivererId);
            return new ResponseEntity<>("Employee erased", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
