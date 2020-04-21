package org.dfarras.simulation.web;

import org.dfarras.simulation.configuration.models.ContagionConfig;
import org.dfarras.simulation.configuration.models.SimulationConfig;
import org.dfarras.simulation.configuration.models.StartConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRQ {
    private StartConfig startConfiguration;
    private SimulationConfig simulationConfiguration;
    private ContagionConfig contagionConfiguration;
}
