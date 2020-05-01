package org.dfarras.simulation.core;

import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;

@Component
public class PlaceCapacityTracker {
    private final InheritableThreadLocal<Map<Place, List<Person>>> locations = new InheritableThreadLocal<>();

    @Autowired
    public PlaceCapacityTracker() {
        locations.set(new HashMap<>());
    }

    public List<Person> getPeopleOf(Place place) {
        synchronized (locations) {
            return locations.get().get(place);
        }
    }

    public void addPlace(Place place) {
        synchronized (locations) {
            locations.get().put(place, new ArrayList<>());
        }
    }

    public void updatePersonLocation(Person person, Place place, LocalTime localTime) {
        synchronized (locations) {
            Place oldPlace = person.whereIAm(localTime);
            locations.get().get(oldPlace).removeIf(target -> target.equals(person));
            locations.get().get(place).add(person);
        }
    }
}
