package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.service;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.DeliveryEmployee;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryEmployeeService {

    DeliveryEmployee createEmployee(DeliveryEmployee deliveryEmployee);

    DeliveryEmployee findEmployee(Long id);

    DeliveryEmployee alterEmployee(Long id, DeliveryEmployee deliveryEmployee);

    boolean deleteEmployee(Long id);

}
