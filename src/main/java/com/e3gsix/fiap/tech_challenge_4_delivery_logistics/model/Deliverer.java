package com.e3gsix.fiap.tech_challenge_4_delivery_logistics.model;

import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.UF;
import com.e3gsix.fiap.tech_challenge_4_delivery_logistics.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "deliverer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deliverer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotEmpty(message = "usuário não pode estar vazio")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "veiculo não pode estar vazio")
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "uf não pode estar vazio")
    private UF uf;
}
