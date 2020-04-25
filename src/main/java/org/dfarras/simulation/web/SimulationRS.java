package org.dfarras.simulation.web;

import org.dfarras.simulation.configuration.models.ContagionConfig;
import org.dfarras.simulation.configuration.models.SimulationConfig;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.core.data.SimulationData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRS {
    private LocalDateTime startTime;
    private LocalDateTime simulationTime;
    private long totalInfected;
    private SimulationConfiguration configuration;
}
