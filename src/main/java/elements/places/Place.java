package elements.places;

import core.start.InfectionStrategy;
import elements.Person;

import java.util.List;

public interface Place {
    List<Person> getPeople();
    InfectionStrategy getInfectionStrategy();
}
