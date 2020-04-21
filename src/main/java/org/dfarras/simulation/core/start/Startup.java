package org.dfarras.simulation.core.start;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.elements.ElementFactory;
import org.dfarras.simulation.elements.places.House;
import org.dfarras.simulation.elements.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Startup {
    private ConfigurationManager configurationManager;
    private InfectionStrategy infectionStrategy;
    private ElementFactory elementFactory;
    private SimulationDataManager simulationDataManager;

    @Autowired
    public Startup(ConfigurationManager configurationManager, AbsoluteInfectionStrategy absoluteInfectionStrategy, ElementFactory elementFactory, SimulationDataManager simulationDataManager) {
        this.configurationManager = configurationManager;
        this.infectionStrategy = absoluteInfectionStrategy;
        this.elementFactory = elementFactory;
        this.simulationDataManager = simulationDataManager;
    }

    public List<Person> buildSimulationData() {
        SimulationData simulationData = elementFactory.getNewSimulationData();
        simulationDataManager.addSimulation(simulationData);
        StartConfig startConfig = this.configurationManager.getStartConfig();
        Long population = startConfig.getPopulation();
        Integer personPerHouse = startConfig.getPersonPerHouse();
        List<Person> persons = new ArrayList<>();

        House house = elementFactory.getNewHouse();
        int peopleInHouse = 0;
        long actualCreatedPopulation = 0L;
        while(actualCreatedPopulation < population) {
            if(peopleInHouse >=  personPerHouse) {
                house = elementFactory.getNewHouse();
                peopleInHouse = 0;
            }
            persons.add(elementFactory.getNewPerson(house, infectionStrategy.isInfected()));
            peopleInHouse++;
            actualCreatedPopulation++;
        }
        return persons;
    }
}
