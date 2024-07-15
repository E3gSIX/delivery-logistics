package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.DeliveryEmployeeController;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DeliveryEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryEmployee")
@RequiredArgsConstructor
public class DeliveryEmployeeControllerImpl implements DeliveryEmployeeController {
    private final DeliveryEmployeeService deliveryEmployeeService;

    @PostMapping
    @Override
    public ResponseEntity<DeliveryEmployee> createEmployee(@RequestBody DeliveryEmployee deliveryEmployee) {
        return new ResponseEntity<>(deliveryEmployeeService.createEmployee(deliveryEmployee), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Long id) {
        try {
            DeliveryEmployee employee = deliveryEmployeeService.findEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterEmployee(@PathVariable Long id, @RequestBody DeliveryEmployee deliveryEmployee) {
        try {
            DeliveryEmployee employee = deliveryEmployeeService.alterEmployee(id, deliveryEmployee);
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
            deliveryEmployeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee erased", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
