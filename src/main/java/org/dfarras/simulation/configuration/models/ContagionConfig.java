package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.*;

@Data
public class ContagionConfig {
    @NotNull
    @Max(100)
    @PositiveOrZero
    private Double houseSpreadChance;
    @NotNull
    @Max(100)
    @PositiveOrZero
    private Double workplaceSpreadChance;
}
