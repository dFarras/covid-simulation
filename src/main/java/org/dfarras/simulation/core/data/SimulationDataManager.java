package org.dfarras.simulation.core.data;

import org.springframework.stereotype.Component;

@Component
public class SimulationDataManager {
    private InheritableThreadLocal<SimulationData> simulations = new InheritableThreadLocal<>();

    public void addSimulation(SimulationData simulationData) {
        simulations.set(simulationData);
    }

    public SimulationData getCurrentSimulation() {
        return simulations.get();
    }

    public void removeSimulation() {
        simulations.remove();
    }
}
