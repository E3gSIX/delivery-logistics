package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service.impl;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererDTO;
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
    public DelivererDTO findById(Long id) {
        Deliverer findedDeliverer = delivererRepository.findById(id)
                .orElseThrow(() -> createNotFoundOrderException(id));

        return DelivererDTO.fromModel(findedDeliverer);
    }

    @Override
    public DelivererDTO update(Long id, Deliverer alterEmployee) {
        Deliverer delivererToUpdate = delivererRepository.findById(id)
                .orElseThrow(() -> createNotFoundOrderException(id));

        if (!alterEmployee.getId().equals(id)) {
            throw new NotFoundException("Entregador não apresenta o ID correto");
        }

        delivererToUpdate.setName(alterEmployee.getName());
        delivererToUpdate.setUf(alterEmployee.getUf());
        delivererToUpdate.setVehicleType(alterEmployee.getVehicleType());

        Deliverer updatedDeliverer = this.delivererRepository.save(delivererToUpdate);

        return DelivererDTO.fromModel(updatedDeliverer);
    }

    @Override
    public void delete(Long id) {
        this.delivererRepository.deleteById(id);
    }

     private NotFoundException createNotFoundOrderException(Long id){
        return new NotFoundException("Entregador com ID '" + id + "' não encontrado.");
    }
}
