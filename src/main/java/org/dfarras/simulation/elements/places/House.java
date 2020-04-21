package org.dfarras.simulation.elements.places;

import org.dfarras.simulation.core.start.InfectionStrategy;
import org.dfarras.simulation.elements.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House implements Place {
    private List<Person> people = new ArrayList<>();
    private InfectionStrategy infectionStrategy;
}
