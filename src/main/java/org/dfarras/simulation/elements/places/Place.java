package org.dfarras.simulation.elements.places;

import org.dfarras.simulation.core.start.InfectionStrategy;
import org.dfarras.simulation.elements.Person;

import java.util.List;

public interface Place {
    List<Person> getPeople();
    InfectionStrategy getInfectionStrategy();
}
