package configuration;

import configuration.models.ContagionConfig;
import configuration.models.StartConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class ConfigurationManager {
    private static StartConfig startConfig = getConfiguration("start-config.yml");
    private static ContagionConfig contagionConfig = getConfiguration("contagion-config.yml");
    private static configuration.models.SimulationConfig simulationConfig = getConfiguration("simulation-config.yml");

    private static  <T> T getConfiguration(String configPath) {
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigurationManager.class
                .getClassLoader()
                .getResourceAsStream(configPath);
        return yaml.load(inputStream);
    }

    public static StartConfig getStartConfig() {
        return startConfig;
    }

    public static ContagionConfig getContagionConfig() {
        return contagionConfig;
    }

    public static configuration.models.SimulationConfig getSimulationConfig() {return simulationConfig;}
}
