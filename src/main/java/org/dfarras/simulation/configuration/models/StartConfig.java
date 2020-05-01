package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.*;

@Data
public class StartConfig {
    @NotNull
    @Max(10001)
    @PositiveOrZero
    private Long population;
    @NotNull
    @PositiveOrZero
    private Integer personPerHouse;
    @NotNull
    @PositiveOrZero
    private Integer personPerWorkplace;
    @NotNull
    @PositiveOrZero
    private Long initialInfectedPopulation;
}
