package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto.DelivererCreationRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;

public interface DelivererService {

    Long create(DelivererCreationRequestDTO deliverer);

    Deliverer findById(Long id);

    Deliverer alterEmployee(Long id, Deliverer deliverer);

    void deleteEmployee(Long id);

}
