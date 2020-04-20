package core;

import configuration.ConfigurationManager;
import core.data.InfectionMediator;
import core.data.SimulationData;
import core.start.InfectionStrategy;
import elements.Person;
import elements.places.House;

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
                        House house = infectedPerson.getHouse();
                        InfectionStrategy infectionStrategy = house.getInfectionStrategy();
                        house.getPeople().stream()
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
