package org.dfarras.simulation.configuration.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "schedule-config")
public class ScheduleConfig {
    private List<ScheduleEntry> scheduleEntries;
}
