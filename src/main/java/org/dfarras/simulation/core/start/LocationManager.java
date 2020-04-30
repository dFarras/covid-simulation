package org.dfarras.simulation.core.start;

import org.dfarras.simulation.elements.places.Place;
import org.dfarras.simulation.exceptions.ElementCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationManager {
    private static final List<Place> availablePlaces = new ArrayList<>();
    private static final String PLACES_PATH_PREFIX = "org.dfarras.simulation.elements.places.";
    private PlaceFactory placeFactory;

    @Autowired
    public LocationManager(PlaceFactory placeFactory) {
        this.placeFactory = placeFactory;
    }

    public Place getPlace(String className) {
        return getPlace(getPlaceClass(className));
    }

    public <T extends Place> Place getPlace(Class<T> clazz) {
        Place result;
        synchronized (availablePlaces) {
            result = availablePlaces.stream()
                    .filter(clazz::isInstance)
                    .findFirst()
                    .orElseGet(() -> {
                        Place place = placeFactory.getNewPlace(clazz);
                        availablePlaces.add(place);
                        return place;
                    });
            result.increaseCapacity();
            if (result.isFull()) {
                availablePlaces.remove(result);
            }
        }
        return result;
    }

    private Class<? extends Place> getPlaceClass(String placeClassName) {
        Class<? extends Place> target;
        try {
            target = Class.forName(PLACES_PATH_PREFIX + placeClassName).asSubclass(Place.class);
        } catch (ClassNotFoundException e) {
            throw new ElementCreationException(e.getMessage(), e);
        }
        return target;
    }
}
