package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.DelivererController;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DelivererService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl.DelivererControllerImpl.URL_DELIVERER;

@RestController
@RequestMapping(URL_DELIVERER)
@RequiredArgsConstructor
public class DelivererControllerImpl implements DelivererController {

    public static final String URL_DELIVERER = "/deliverer";

    private final DelivererService delivererService;

    @PostMapping
    @Override
    public ResponseEntity<Deliverer> createEmployee(@RequestBody Deliverer deliverer) {
        return new ResponseEntity<>(delivererService.createEmployee(deliverer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Long id) {
        try {
            Deliverer employee = delivererService.findEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterEmployee(@PathVariable Long id, @RequestBody Deliverer deliverer) {
        try {
            Deliverer employee = delivererService.alterEmployee(id, deliverer);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            delivererService.deleteEmployee(id);
            return new ResponseEntity<>("Employee erased", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
