package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
public class DelivererTest {

    @Test
    public void getId_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(1L, deliverer.getId().longValue());
    }

    @Test
    public void getName_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals("Randon", deliverer.getName());
    }

    @Test
    public void getVehicleType_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(VehicleType.CAR, deliverer.getVehicleType());
    }

    @Test
    public void getUf_WithValue_successful() {
        Deliverer deliverer = new Deliverer(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(Uf.SAO_PAULO, deliverer.getUf());
    }
}
