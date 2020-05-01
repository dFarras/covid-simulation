package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.*;

@Data
public class SimulationConfig {
    @NotNull
    @Max(160)
    @PositiveOrZero
    private Long totalSimulatedHours;
}
