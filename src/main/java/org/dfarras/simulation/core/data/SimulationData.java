package org.dfarras.simulation.core.data;

import lombok.Data;
import org.dfarras.simulation.configuration.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class SimulationData implements InfectionObserver {
    private static final Double MILLIS_PER_DAY = 86400D;
    private LocalDateTime startTime;
    private LocalDateTime simulationTime;
    private long totalInfected;
    private List<HourData> hourlyReport;
    private ConfigurationManager configurationManager;

    @Autowired
    public SimulationData(ConfigurationManager configurationManager) {
        this.startTime = LocalDateTime.now();
        this.simulationTime = startTime;
        this.hourlyReport = new ArrayList<>();
        this.configurationManager = configurationManager;
    }

    public void addFirstHourReport() {
        this.hourlyReport.add(HourData.builder()
                .hour(0L)
                .totalInfectedPopulation(this.totalInfected)
                .build());
    }

    public void addOneHour() {
        simulationTime = simulationTime.plusHours(1);
        this.hourlyReport.add(HourData.builder()
                .hour(getActualHour())
                .totalInfectedPopulation(this.totalInfected)
                .build());
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

    private Long getActualHour() {
        return this.startTime.until(this.simulationTime, ChronoUnit.HOURS);
    }
}
