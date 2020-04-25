package org.dfarras.simulation.core.data;

import org.dfarras.simulation.core.start.SimulationDataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulationDataManager {
    private InheritableThreadLocal<SimulationData> simulations = new InheritableThreadLocal<>();
    private SimulationDataFactory simulationDataFactory;

    @Autowired
    public SimulationDataManager(SimulationDataFactory simulationDataFactory) {
        this.simulationDataFactory = simulationDataFactory;
    }

    public void addSimulation() {
        simulations.set(simulationDataFactory.getNewSimulationData());
    }

    public SimulationData getCurrentSimulation() {
        return simulations.get();
    }

    public void removeSimulation() {
        simulations.remove();
    }
}
