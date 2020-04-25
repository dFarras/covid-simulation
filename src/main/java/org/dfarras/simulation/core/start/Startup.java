package org.dfarras.simulation.core.start;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.elements.places.House;
import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.infection.AbsoluteInfectionStrategy;
import org.dfarras.simulation.infection.InfectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Startup {
    private ConfigurationManager configurationManager;
    private InfectionStrategy infectionStrategy;
    private PlaceFactory placeFactory;
    private PersonFactory personFactory;
    private SimulationDataManager simulationDataManager;

    @Autowired
    public Startup(ConfigurationManager configurationManager,
                   AbsoluteInfectionStrategy absoluteInfectionStrategy,
                   PlaceFactory placeFactory,
                   PersonFactory personFactory,
                   SimulationDataManager simulationDataManager) {
        this.configurationManager = configurationManager;
        this.infectionStrategy = absoluteInfectionStrategy;
        this.placeFactory = placeFactory;
        this.personFactory = personFactory;
        this.simulationDataManager = simulationDataManager;
    }

    public List<Person> buildSimulationData() {
        simulationDataManager.addSimulation();
        StartConfig startConfig = this.configurationManager.getStartConfig();
        Long population = startConfig.getPopulation();
        Integer personPerHouse = startConfig.getPersonPerHouse();
        List<Person> persons = new ArrayList<>();

        House house = placeFactory.getNewHouse();
        long createdPopulation = 0L;
        while(createdPopulation < population) {
            persons.add(personFactory.getNewPerson(infectionStrategy.isInfected(), simulationDataManager.getCurrentSimulation().getSimulationTime().toLocalTime()));
            createdPopulation++;
        }
        return persons;
    }
}
