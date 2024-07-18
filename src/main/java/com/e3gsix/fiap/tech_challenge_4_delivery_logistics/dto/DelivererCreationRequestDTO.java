package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.dto;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;

public record DelivererCreationRequestDTO(
        String name,
        VehicleType vehicleType,
        UF uf
) {
    public Deliverer toModel(){
        return new Deliverer(
                null,
                this.name,
                this.vehicleType,
                this.uf
        );
    }
}
