package org.dfarras.simulation.elements.places;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dfarras.simulation.infection.InfectionStrategy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workplace implements Place {
    private int capacity;
    private int actualCapacity;
    private InfectionStrategy infectionStrategy;

    @Override
    public boolean isFull() {
        return this.actualCapacity >= this.capacity;
    }

    @Override
    public void increaseCapacity() {
        this.actualCapacity++;
    }
}
