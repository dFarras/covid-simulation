package org.dfarras.simulation.configuration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartConfig {
    private Long population;
    private Integer personPerHouse;
    private Long initialInfectedPopulation;
}