package org.dfarras.simulation.core;

import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;

@Component
public class PlaceCapacityTracker {
    private static final Map<Place, List<Person>> locations = new HashMap<>();

    public List<Person> getPeopleOf(Place place) {
        return locations.get(place);
    }

    public void addPlace(Place place) {
        locations.put(place, new ArrayList<>());
    }

    public void updatePersonLocation(Person person, Place place, LocalTime localTime) {
        synchronized (locations) {
            Place oldPlace = person.whereIAm(localTime);
            locations.get(oldPlace).removeIf(target -> target.equals(person));
            locations.get(place).add(person);
        }
    }
}
