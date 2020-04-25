package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "simulation-config")
public class SimulationConfig {
    private Long totalSimulatedHours;
}
