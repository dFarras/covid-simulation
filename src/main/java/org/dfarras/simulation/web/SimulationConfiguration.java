package org.dfarras.simulation.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dfarras.simulation.configuration.models.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationConfiguration {
    private Long population;
    private Integer personPerHouse;
    private Long initialInfectedPopulation;
    private Long totalSimulatedHours;
    private Double houseSpreadChance;
    private List<ScheduleEntry> schedule;
}
