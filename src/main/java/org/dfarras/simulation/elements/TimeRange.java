package org.dfarras.simulation.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeRange {
    private LocalTime start;
    private LocalTime end;

    public boolean isInRange(LocalTime time) {
        boolean result;
        if(start.isBefore(end)) {
            result = time.minusMinutes(1).isAfter(start) && time.isBefore(end);
        } else {
            result = (time.isBefore(start) && time.isBefore(end)) || (time.isAfter(start) && time.isAfter(end));
        }
        return result;
    }
}
