package org.dfarras.simulation.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dfarras.simulation.elements.places.Place;
import org.dfarras.simulation.exceptions.DailyScheduleException;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySchedule {
    private Map<TimeRange, Place> schedule = new HashMap<>();

    public void addScheduleEntry(TimeRange timeRange, Place place) {
        schedule.put(timeRange, place);
    }

    public Place locationOn(LocalTime time) {
        return schedule.entrySet().stream()
                .filter(entry -> entry.getKey().isInRange(time))
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new DailyScheduleException(String.format("Could not find a place for time [%s]", time.toString())));
    }
}
