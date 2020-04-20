package core;

import configuration.ConfigurationManager;
import core.data.SimulationData;
import core.start.InfectionStrategy;
import elements.Person;
import elements.places.Place;

import java.util.List;

public class Simulation {
    private configuration.models.SimulationConfig simulationConfig = ConfigurationManager.getSimulationConfig();

    public SimulationData runSimulation(List<Person> population) {
        int hourRun = 0;
        SimulationData simulationData = SimulationData.getInstance();
        while (hourRun < simulationConfig.getTotalSimulatedHours()) {
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
        return simulationData;
    }
}
