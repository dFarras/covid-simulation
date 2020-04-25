package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "contagion-config")
public class ContagionConfig {
    private Double houseSpreadChance;
    private Double workplaceSpreadChance;
}
