package core.start;

import configuration.ConfigurationManager;
import configuration.models.StartConfig;
import core.ChanceRoll;
import elements.Person;

public class AbsoluteInfectionStrategy extends InfectionStrategy {
    private ChanceRoll chanceRoll = new ChanceRoll();
    private Double initialInfectionChance;

    {
        StartConfig startConfig = ConfigurationManager.getStartConfig();
        Long initialInfectedPopulation = startConfig.getInitialInfectedPopulation();
        initialInfectionChance = initialInfectedPopulation / (double) startConfig.getPopulation() * 100.00d;
    }

    @Override
    //TODO Check that the person is not infected, it would make no sense to check it here!!!
    public boolean isInfected() {
        boolean result = chanceRoll.percentRoll(initialInfectionChance);
        if (result) {
            infectionMediator.signalObserver();
        }
        return result;
    }

    @Override
    public boolean isSpread(Person person) {
        return false;
    }
}
