package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;

public interface DelivererService {

    Long create(DelivererCreationRequestDTO deliverer);

    DelivererDTO findById(Long id);

    DelivererDTO update(Long id, Deliverer deliverer);

    void delete(Long id);

}
