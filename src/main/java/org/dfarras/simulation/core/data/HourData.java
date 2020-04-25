package org.dfarras.simulation.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HourData {
    private Long hour;
    private Long totalInfectedPopulation;
}
