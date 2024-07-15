package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.Uf;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.util.enums.VehicleType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
public class DeliveryEmployeeTest {

    @Test
    public void getId_WithValue_successful() {
        DeliveryEmployee deliveryemployee = new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(1L, deliveryemployee.getId().longValue());
    }

    @Test
    public void getName_WithValue_successful() {
        DeliveryEmployee deliveryemployee = new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals("Randon", deliveryemployee.getName());
    }

    @Test
    public void getVehicleType_WithValue_successful() {
        DeliveryEmployee deliveryemployee = new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(VehicleType.CAR, deliveryemployee.getVehicleType());
    }

    @Test
    public void getUf_WithValue_successful() {
        DeliveryEmployee deliveryemployee = new DeliveryEmployee(1L, "Randon", VehicleType.CAR, Uf.SAO_PAULO);
        assertEquals(Uf.SAO_PAULO, deliveryemployee.getUf());
    }
}
