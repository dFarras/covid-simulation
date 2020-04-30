package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Configuration
@ConfigurationProperties(prefix = "contagion-config")
public class ContagionConfig {
    @NotBlank
    @Size(max = 100)
    private Double houseSpreadChance;
    @NotBlank
    @Size(max = 100)
    private Double workplaceSpreadChance;
}
