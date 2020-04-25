package org.dfarras.simulation.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dfarras.simulation.core.data.HourData;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRS {
    private LocalDateTime startTime;
    private LocalDateTime simulationTime;
    private long totalInfected;
    private Double totalSimulatedDays;
    private List<HourData> hourlyReport;
    private SimulationConfiguration configuration;
}
