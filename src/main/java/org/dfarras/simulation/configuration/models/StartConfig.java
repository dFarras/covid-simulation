package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "start-config")
public class StartConfig {
    private Long population;
    private Integer personPerHouse;
    private Integer personPerWorkplace;
    private Long initialInfectedPopulation;
}
