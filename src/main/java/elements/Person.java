package elements;

import elements.places.House;
import elements.places.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Place location;
    private Boolean infected;
}
