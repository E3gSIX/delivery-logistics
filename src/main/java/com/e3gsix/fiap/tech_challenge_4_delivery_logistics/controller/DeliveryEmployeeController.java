package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.impl.DeliveryEmployeeControllerImpl;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DeliveryEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/deliveryEmployee")
@RequiredArgsConstructor
public class DeliveryEmployeeController implements DeliveryEmployeeControllerImpl {
    private final DeliveryEmployeeService deliveryEmployeeService;

    @PostMapping
    @Override
    public ResponseEntity<DeliveryEmployee> createEmployee(@RequestBody DeliveryEmployee deliveryEmployee) {
        return new ResponseEntity<>(deliveryEmployeeService.createEmployee(deliveryEmployee), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DeliveryEmployee> findEmployee(Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<DeliveryEmployee> alterEmployee(Long orderId, UriComponentsBuilder uriComponentsBuilder) {
        return null;
    }

    @Override
    public ResponseEntity<DeliveryEmployee> alterEmployee(Long orderId) {
        return null;
    }
}
