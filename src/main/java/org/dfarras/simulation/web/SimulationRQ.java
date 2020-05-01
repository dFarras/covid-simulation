package org.dfarras.simulation.web;

import org.dfarras.simulation.configuration.models.ContagionConfig;
import org.dfarras.simulation.configuration.models.SimulationConfig;
import org.dfarras.simulation.configuration.models.StartConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRQ {
    @Valid
    private StartConfig startConfiguration;
    @Valid
    private SimulationConfig simulationConfiguration;
    @Valid
    private ContagionConfig contagionConfiguration;
}
