package org.dfarras.simulation.configuration;

import org.dfarras.simulation.configuration.models.ContagionConfig;
import org.dfarras.simulation.configuration.models.ScheduleConfig;
import org.dfarras.simulation.configuration.models.SimulationConfig;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.web.SimulationRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {
    private StartConfig startConfig;
    private ContagionConfig contagionConfig;
    private SimulationConfig simulationConfig;
    private ScheduleConfig scheduleConfig;

    @Autowired
    public ConfigurationManager(ScheduleConfig scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }

    public StartConfig getStartConfig() {
        return this.startConfig;
    }

    public ContagionConfig getContagionConfig() {
        return this.contagionConfig;
    }

    public SimulationConfig getSimulationConfig() {return this.simulationConfig;}

    public ScheduleConfig getScheduleConfig() {
        return this.scheduleConfig;
    }

    public void overrideConfiguration(SimulationRQ simulationRQ) {
        this.startConfig = simulationRQ.getStartConfiguration();
        this.simulationConfig = simulationRQ.getSimulationConfiguration();
        this.contagionConfig = simulationRQ.getContagionConfiguration();
    }
}
