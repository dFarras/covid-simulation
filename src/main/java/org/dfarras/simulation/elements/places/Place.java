package org.dfarras.simulation.elements.places;

import org.dfarras.simulation.infection.InfectionStrategy;

public interface Place {
    int getCapacity();
    boolean isFull();
    void increaseCapacity();
    InfectionStrategy getInfectionStrategy();
}
