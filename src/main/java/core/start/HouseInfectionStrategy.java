package core.start;

import configuration.ConfigurationManager;
import core.ChanceRoll;
import elements.Person;

public class HouseInfectionStrategy extends InfectionStrategy {
    private static HouseInfectionStrategy houseInfectionStrategy;
    private ChanceRoll chanceRoll = new ChanceRoll();
    private static final Double SPREAD_CHANCE = ConfigurationManager.getContagionConfig().getHouseSpreadChance();

    private static HouseInfectionStrategy getInstance() {
        if(houseInfectionStrategy == null) {
            houseInfectionStrategy = new HouseInfectionStrategy();
        }
        return houseInfectionStrategy;
    }

    @Override
    public boolean isInfected() {
        boolean result = chanceRoll.percentRoll(SPREAD_CHANCE);
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
