package org.dfarras.simulation.core.start;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.core.PlaceCapacityTracker;
import org.dfarras.simulation.elements.places.House;
import org.dfarras.simulation.elements.places.Place;
import org.dfarras.simulation.elements.places.Workplace;
import org.dfarras.simulation.infection.HouseInfectionStrategy;
import org.dfarras.simulation.infection.WorkplaceInfectionStrategy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PlaceFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private PlaceCapacityTracker placeCapacityTracker;
    private ConfigurationManager configurationManager;

    @Autowired
    public PlaceFactory(PlaceCapacityTracker placeCapacityTracker, ConfigurationManager configurationManager) {
        this.placeCapacityTracker = placeCapacityTracker;
        this.configurationManager = configurationManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T extends Place> Place getNewPlace(Class<T> type) {
        Place result;
        if(type.getCanonicalName().equals(House.class.getCanonicalName())) {
            result = getNewHouse();
        } else {
            result = getNewWorkplace();
        }
        return result;
    }

    public House getNewHouse() {
        House house = new House();
        house.setCapacity(configurationManager.getStartConfig().getPersonPerHouse());
        house.setInfectionStrategy(applicationContext.getBean(HouseInfectionStrategy.class));
        placeCapacityTracker.addPlace(house);
        return house;
    }

    public Workplace getNewWorkplace() {
        Workplace workPlace = new Workplace();
        workPlace.setCapacity(configurationManager.getStartConfig().getPersonPerWorkplace());
        workPlace.setInfectionStrategy(applicationContext.getBean(WorkplaceInfectionStrategy.class));
        placeCapacityTracker.addPlace(workPlace);
        return workPlace;
    }
}
