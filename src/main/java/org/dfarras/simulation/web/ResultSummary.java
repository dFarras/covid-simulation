package org.dfarras.simulation.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultSummary {
    private LocalDateTime startTime;
    private LocalDateTime simulationTime;
    private long totalInfected;
    private Double totalSimulatedDays;
}
