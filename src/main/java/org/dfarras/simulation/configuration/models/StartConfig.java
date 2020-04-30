package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Configuration
@ConfigurationProperties(prefix = "start-config")
public class StartConfig {
    @NotBlank
    @Size(min = 100, max = 10000)
    private Long population;
    @NotBlank
    private Integer personPerHouse;
    @NotBlank
    private Integer personPerWorkplace;
    @NotBlank
    private Long initialInfectedPopulation;
}
