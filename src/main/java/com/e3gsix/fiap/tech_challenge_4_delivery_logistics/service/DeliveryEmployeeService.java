package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;

public interface DeliveryEmployeeService {

    DeliveryEmployee createEmployee(DeliveryEmployee deliveryEmployee);

    DeliveryEmployee findEmployee(Long id);

    DeliveryEmployee alterEmployee(Long id, DeliveryEmployee deliveryEmployee);

    void deleteEmployee(Long id);

}
