package org.dfarras.simulation.elements;

import org.dfarras.simulation.elements.places.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Boolean infected;
    private DailySchedule dailySchedule;

    public Place whereIAm(LocalTime time) {
        return dailySchedule.locationOn(time);
    }
}
