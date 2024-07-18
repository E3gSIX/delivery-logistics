package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository.DelivererRepository;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.DelivererService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DelivererServiceImpl implements DelivererService {

    private final DelivererRepository delivererRepository;

    @Override
    public Long create(DelivererCreationRequestDTO delivererDTO) {
        Deliverer createdDeliverer = this.delivererRepository.save(delivererDTO.toModel());
        return createdDeliverer.getId();
    }

    @Override
    public Deliverer findEmployee(Long id) {
       return delivererRepository.findById(id).orElseThrow(() -> createNotFoundOrderException(id));
    }

    @Override
    public Deliverer alterEmployee(Long id, Deliverer alterEmployee) {
        Deliverer employee = findEmployee(id);
        if (!alterEmployee.getId().equals(id)) {
            throw new NotFoundException("Entregador não apresenta o ID correto");
        }
        employee.setName(alterEmployee.getName());
        employee.setUf(alterEmployee.getUf());
        employee.setVehicleType(alterEmployee.getVehicleType());
        return delivererRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        delivererRepository.deleteById(id);
    }

     private NotFoundException createNotFoundOrderException(Long id){
        return new NotFoundException("Entregador com ID: " + id + " não encontrado.");
    }
}
