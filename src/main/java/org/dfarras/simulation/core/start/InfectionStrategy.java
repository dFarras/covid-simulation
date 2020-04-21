package org.dfarras.simulation.core.start;

import org.dfarras.simulation.elements.Person;

public interface InfectionStrategy {
    boolean isInfected();
    boolean isSpread(Person person);
}
