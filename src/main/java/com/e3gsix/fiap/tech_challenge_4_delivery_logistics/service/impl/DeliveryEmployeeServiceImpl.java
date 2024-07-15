package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DeliveryEmployeeRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DeliveryEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryEmployeeServiceImpl implements DeliveryEmployeeService {

    private final DeliveryEmployeeRepository deliveryEmployeeRepository;

    @Override
    public DeliveryEmployee createEmployee(DeliveryEmployee deliveryEmployee) {
        return deliveryEmployeeRepository.save(deliveryEmployee);
    }

    @Override
    public DeliveryEmployee findEmployee(Long id) {
        validateExistence(id);
        return deliveryEmployeeRepository.findById(id).orElseThrow(() -> createNotFoundOrderException(id));
    }

    @Override
    public DeliveryEmployee alterEmployee(Long id, DeliveryEmployee alterEmployee) {
        DeliveryEmployee employee = findEmployee(id);
        if (!employee.getId().equals(id)) {
            throw new NotFoundException("Empregado não apresenta o ID correto");
        }
        employee.setName(alterEmployee.getName());
        employee.setUf(alterEmployee.getUf());
        employee.setVehicleType(alterEmployee.getVehicleType());
        return deliveryEmployeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        DeliveryEmployee employee = findEmployee(id);
        deliveryEmployeeRepository.delete(employee);
        return true;
    }

    private void validateExistence(Long id) {
        if (!this.deliveryEmployeeRepository.existsById(id)) throw createNotFoundOrderException(id);
    }

    private NotFoundException createNotFoundOrderException(Long id){
        return new NotFoundException("Employee with ID: " + id + " not found.");
    }
}
