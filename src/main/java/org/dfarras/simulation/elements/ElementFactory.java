package org.dfarras.simulation.elements;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.start.HouseInfectionStrategy;
import org.dfarras.simulation.elements.places.House;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ElementFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private ConfigurationManager configurationManager;

    public ElementFactory(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public Person getNewPerson(Place location, boolean infected) {
        Person person = applicationContext.getBean(Person.class);
        person.setLocation(location);
        person.setInfected(infected);
        return person;
    }

    public House getNewHouse() {
        House house = applicationContext.getBean(House.class);
        house.setInfectionStrategy(applicationContext.getBean(HouseInfectionStrategy.class));
        return house;
    }

    public SimulationData getNewSimulationData() {
        return applicationContext.getBean(SimulationData.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
