package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
public class DelivererTest {

    @Test
    public void getId_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
        assertEquals(1L, deliverer.getId().longValue());
    }

    @Test
    public void getName_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
        assertEquals("Randon", deliverer.getName());
    }

    @Test
    public void getVehicleType_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
        assertEquals(VehicleType.CAR, deliverer.getVehicleType());
    }

    @Test
    public void getUf_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, UF.SP);
        assertEquals(UF.SP, deliverer.getUf());
    }
}
