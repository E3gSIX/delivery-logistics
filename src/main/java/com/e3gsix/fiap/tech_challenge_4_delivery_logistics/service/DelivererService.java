package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;

public interface DelivererService {

    Deliverer createEmployee(Deliverer deliverer);

    Deliverer findEmployee(Long id);

    Deliverer alterEmployee(Long id, Deliverer deliverer);

    void deleteEmployee(Long id);

}
