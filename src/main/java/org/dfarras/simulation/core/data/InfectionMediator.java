package org.dfarras.simulation.core.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfectionMediator {
    private SimulationDataManager simulationDataManager;

    @Autowired
    public InfectionMediator(SimulationDataManager simulationDataManager) {
        this.simulationDataManager = simulationDataManager;
    }

    public void signalObserver() {
        getObserver().receive();
    }

    private InfectionObserver getObserver() {
        return simulationDataManager.getCurrentSimulation();
    }
}
