package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.repository;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model.Deliverer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelivererRepository extends JpaRepository<Deliverer, Long> {
}
