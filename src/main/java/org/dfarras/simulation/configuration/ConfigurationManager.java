package org.dfarras.simulation.configuration;

import org.dfarras.simulation.configuration.models.ContagionConfig;
import org.dfarras.simulation.configuration.models.StartConfig;
import org.dfarras.simulation.configuration.models.SimulationConfig;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.dfarras.simulation.web.SimulationRQ;

import java.io.InputStream;

@Component
public class ConfigurationManager {
    private StartConfig startConfig = getConfiguration("start-config.yml");
    private ContagionConfig contagionConfig = getConfiguration("contagion-config.yml");
    private SimulationConfig simulationConfig = getConfiguration("simulation-config.yml");

    private static  <T> T getConfiguration(String configPath) {
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigurationManager.class
                .getClassLoader()
                .getResourceAsStream(configPath);
        return yaml.load(inputStream);
    }

    public StartConfig getStartConfig() {
        return this.startConfig;
    }

    public ContagionConfig getContagionConfig() {
        return this.contagionConfig;
    }

    public SimulationConfig getSimulationConfig() {return this.simulationConfig;}

    public void overrideConfiguration(SimulationRQ simulationRQ) {
        this.startConfig = simulationRQ.getStartConfiguration();
        this.simulationConfig = simulationRQ.getSimulationConfiguration();
        this.contagionConfig = simulationRQ.getContagionConfiguration();
    }
}
