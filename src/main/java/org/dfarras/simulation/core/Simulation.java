package org.dfarras.simulation.core;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.infection.InfectionStrategy;
import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Simulation {
    private SimulationDataManager simulationDataManager;
    private ConfigurationManager configurationManager;
    private PlaceCapacityTracker placeCapacityTracker;

    @Autowired
    public Simulation(SimulationDataManager simulationDataManager,
                      ConfigurationManager configurationManager, PlaceCapacityTracker placeCapacityTracker) {
        this.simulationDataManager = simulationDataManager;
        this.configurationManager = configurationManager;
        this.placeCapacityTracker = placeCapacityTracker;
    }

    public void runSimulation(List<Person> population) {
        SimulationData simulationData = simulationDataManager.getCurrentSimulation();
        int hourRun = 0;
        long hoursToSimulate = configurationManager.getSimulationConfig().getTotalSimulatedHours();
        while (hourRun < hoursToSimulate) {
            population.forEach(person -> {
                if (person.getInfected()) {
                    spreadDisease(person, simulationData);
                }
                Place updatedLocation = person.whereIAm(simulationData.getSimulationTime().toLocalTime());
                placeCapacityTracker.updatePersonLocation(person, updatedLocation, simulationData.getSimulationTime().toLocalTime());
            });
            simulationData.addOneHour();
            hourRun++;
        }
    }

    private void spreadDisease(Person infectedPerson, SimulationData simulationData) {
        Place location = infectedPerson.whereIAm(simulationData.getSimulationTime().toLocalTime());
        InfectionStrategy infectionStrategy = location.getInfectionStrategy();
        placeCapacityTracker.getPeopleOf(location)
                .stream()
                .filter(target -> !target.getInfected())
                .forEach(target -> {
                    target.setInfected(infectionStrategy.isInfected());
                });
    }
}
