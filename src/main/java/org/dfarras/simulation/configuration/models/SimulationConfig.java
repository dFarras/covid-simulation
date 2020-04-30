package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Configuration
@ConfigurationProperties(prefix = "simulation-config")
public class SimulationConfig {
    @NotBlank
    @Size(min = 1, max = 300)
    private Long totalSimulatedHours;
}
