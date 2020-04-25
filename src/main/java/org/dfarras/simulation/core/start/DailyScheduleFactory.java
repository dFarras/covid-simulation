package org.dfarras.simulation.core.start;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.configuration.models.ScheduleEntry;
import org.dfarras.simulation.core.PlaceCapacityTracker;
import org.dfarras.simulation.elements.DailySchedule;
import org.dfarras.simulation.elements.TimeRange;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyScheduleFactory {
    private ConfigurationManager configurationManager;
    private LocationManager locationManager;
    private PlaceCapacityTracker placeCapacityTracker;

    @Autowired
    public DailyScheduleFactory(ConfigurationManager configurationManager, LocationManager locationManager, PlaceCapacityTracker placeCapacityTracker) {
        this.configurationManager = configurationManager;
        this.locationManager = locationManager;
        this.placeCapacityTracker = placeCapacityTracker;
    }

    public DailySchedule getDailySchedule() {
        DailySchedule result = new DailySchedule();
        List<ScheduleEntry> scheduleEntries = configurationManager.getScheduleConfig().getScheduleEntries();
        scheduleEntries.forEach(entry -> {
            Place place = locationManager.getPlace(entry.getPlace());
            placeCapacityTracker.addPlace(place);
            TimeRange timeRange = new TimeRange(entry.getStart(), entry.getEnd());
            result.addScheduleEntry(timeRange, place);
        });
        return result;
    }
}
