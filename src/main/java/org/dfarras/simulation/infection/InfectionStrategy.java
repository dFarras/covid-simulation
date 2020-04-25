package org.dfarras.simulation.infection;

import org.dfarras.simulation.elements.Person;

public interface InfectionStrategy {
    boolean isInfected();
    boolean isSpread(Person person);
}
