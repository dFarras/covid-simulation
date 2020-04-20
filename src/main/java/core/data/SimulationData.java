package core.data;

import configuration.ConfigurationManager;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimulationData implements InfectionObserver {
    private static SimulationData instance;
    private static final Double MILLIS_PER_DAY = 86400D;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime simulationTime = startTime;
    private Long totalPopulation;
    private Long totalInfected = 0L;

    {
        totalPopulation = ConfigurationManager.getStartConfig().getPopulation();
    }

    public static SimulationData getInstance() {
        if(instance == null) {
            instance = new SimulationData();
        }
        return instance;
    }

    public void addOneHour() {
        simulationTime = simulationTime.plusHours(1);
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
