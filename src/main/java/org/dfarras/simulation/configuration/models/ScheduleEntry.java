package org.dfarras.simulation.configuration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntry {
    private String place;
    private LocalTime start;
    private LocalTime end;
}
