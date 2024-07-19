package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;

public record DelivererDTO(
        Long id,
        String name,
        VehicleType vehicleType,
        UF uf
) {
    public static DelivererDTO fromModel(Deliverer deliverer) {
        return new DelivererDTO(
                deliverer.getId(),
                deliverer.getName(),
                deliverer.getVehicleType(),
                deliverer.getUf()
        );
    }
}
