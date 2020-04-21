package org.dfarras.simulation.core.data;

import org.dfarras.simulation.configuration.ConfigurationManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class SimulationData implements InfectionObserver {
    private static final Double MILLIS_PER_DAY = 86400D;
    private LocalDateTime startTime;
    private LocalDateTime simulationTime;
    private long totalInfected;
    private ConfigurationManager configurationManager;

    @Autowired
    public SimulationData(ConfigurationManager configurationManager) {
        this.startTime = LocalDateTime.now();
        this.simulationTime = startTime;
        this.configurationManager = configurationManager;
    }

    public void addOneHour() {
        simulationTime = simulationTime.plusHours(1);
    }

    public Long getInitialPopulation() {
        return configurationManager.getStartConfig().getPopulation();
    }

    public double getTotalSimulatedDays() {
        ZoneId zoneId = ZoneId.systemDefault();
        Double elapsedTime = (double) (simulationTime.atZone(zoneId).toEpochSecond() - startTime.atZone(zoneId).toEpochSecond());
        return elapsedTime / MILLIS_PER_DAY;
    }

    @Override
    public void receive() {
        totalInfected++;
    }
}
