package elements.places;

import configuration.ConfigurationManager;
import core.start.HouseInfectionStrategy;
import core.start.InfectionStrategy;
import elements.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House implements Place {
    private List<Person> people = new ArrayList<>();
    private int totalDwellers;
    private InfectionStrategy infectionStrategy = new HouseInfectionStrategy();
}
