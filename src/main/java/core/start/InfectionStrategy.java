package core.start;

import core.data.InfectionMediator;
import elements.Person;

public abstract class InfectionStrategy {
    protected InfectionMediator infectionMediator = InfectionMediator.getInfectionMediator();

    public abstract boolean isInfected();
    public abstract boolean isSpread(Person person);
}
