package core.start;

import configuration.ConfigurationManager;
import configuration.models.StartConfig;
import elements.places.House;
import elements.Person;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    private StartConfig startConfig = ConfigurationManager.getStartConfig();
    private InfectionStrategy infectionStrategy;

    {
        if(startConfig.getInitialInfectedPopulation() != null) {
            infectionStrategy = new AbsoluteInfectionStrategy();
        }
    }

    public List<Person> buildSimulationData() {
        Long population = startConfig.getPopulation();
        Integer personPerHouse = startConfig.getPersonPerHouse();
        List<Person> persons = new ArrayList<>();

        House house = new House();
        long actualCreatedPopulation = 0L;
        while(actualCreatedPopulation < population) {
            if(house.getTotalDwellers() >=  personPerHouse) {
                house = new House();
            }
            persons.add(buildPerson(house));
            actualCreatedPopulation++;
        }
        return persons;
    }

    private Person buildPerson(House house) {
        return new Person(house, infectionStrategy.isInfected());
    }
}
