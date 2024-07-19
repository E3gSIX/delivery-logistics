package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.DelivererController;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DelivererService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<DelivererDTO> findById(@PathVariable Long delivererId) {
        DelivererDTO deliverer = this.delivererService.findById(delivererId);
        return ResponseEntity.ok(deliverer);
    }

    @PutMapping(URL_DELIVERER_ID)
    public ResponseEntity<?> update(@PathVariable Long delivererId, @RequestBody Deliverer deliverer) {
        DelivererDTO updatedDeliverer = this.delivererService.update(delivererId, deliverer);
        return ResponseEntity.ok(updatedDeliverer);
    }

    @DeleteMapping(URL_DELIVERER_ID)
    public ResponseEntity delete(@PathVariable Long delivererId) {
        this.delivererService.delete(delivererId);
        return ResponseEntity.noContent().build();
    }
}
