package org.dfarras.simulation.core;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.core.start.InfectionStrategy;
import org.dfarras.simulation.elements.ElementFactory;
import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Simulation {
    private SimulationDataManager simulationDataManager;
    private ConfigurationManager configurationManager;
    private ElementFactory elementFactory;

    @Autowired
    public Simulation(SimulationDataManager simulationDataManager, ConfigurationManager configurationManager, ElementFactory elementFactory) {
        this.simulationDataManager = simulationDataManager;
        this.configurationManager = configurationManager;
        this.elementFactory = elementFactory;
    }

    public void runSimulation(List<Person> population) {
        SimulationData simulationData = simulationDataManager.getCurrentSimulation();
        int hourRun = 0;
        long hoursToSimulate = configurationManager.getSimulationConfig().getTotalSimulatedHours();
        while (hourRun < hoursToSimulate) {
            population.stream()
                    .filter(Person::getInfected)
                    .forEach(infectedPerson -> {
                        Place location = infectedPerson.getLocation();
                        InfectionStrategy infectionStrategy = location.getInfectionStrategy();
                        location.getPeople().stream()
                                .filter(target -> !target.getInfected())
                                .forEach(target -> {
                                    infectedPerson.setInfected(infectionStrategy.isInfected());
                                });
                    });
            simulationData.addOneHour();
            hourRun++;
        }
    }
}
