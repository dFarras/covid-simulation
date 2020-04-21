package org.dfarras.simulation.core.start;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.core.ChanceRoll;
import org.dfarras.simulation.core.data.InfectionMediator;
import org.dfarras.simulation.elements.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbsoluteInfectionStrategy implements InfectionStrategy {
    private ChanceRoll chanceRoll;
    private ConfigurationManager configurationManager;
    private InfectionMediator infectionMediator;

    @Autowired
    public AbsoluteInfectionStrategy(ChanceRoll chanceRoll, ConfigurationManager configurationManager, InfectionMediator infectionMediator) {
        this.chanceRoll = chanceRoll;
        this.configurationManager = configurationManager;
        this.infectionMediator = infectionMediator;
    }

    private Double getInitialInfectionChance() {
        StartConfig startConfig = configurationManager.getStartConfig();
        Long initialInfectedPopulation = startConfig.getInitialInfectedPopulation();
        return initialInfectedPopulation / (double) startConfig.getPopulation() * 100.00d;
    }

    @Override
    public boolean isInfected() {
        boolean result = chanceRoll.percentRoll(getInitialInfectionChance());
        if (result) {
            this.infectionMediator.signalObserver();
        }
        return result;
    }

    @Override
    public boolean isSpread(Person person) {
        return false;
    }
}
