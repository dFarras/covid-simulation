package org.dfarras.simulation.web;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.configuration.models.*;
import org.dfarras.simulation.core.Simulation;
import org.dfarras.simulation.core.data.SimulationData;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.core.start.Startup;
import org.dfarras.simulation.elements.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/covid-simulation")
public class SimulationWebStarter {
    private Simulation simulation;
    private Startup startup;
    private SimulationDataManager simulationDataManager;
    private ConfigurationManager configurationManager;

    @Autowired
    public SimulationWebStarter(Simulation simulation, Startup startup, SimulationDataManager simulationDataManager, ConfigurationManager configurationManager) {
        this.simulation = simulation;
        this.startup = startup;
        this.simulationDataManager = simulationDataManager;
        this.configurationManager = configurationManager;
    }

    /**
     * Endpoint to start a simulation
     *
     * @return simulation info result
     */
    @PostMapping(path = "/start-simulation",
            consumes = {"application/json"},
            produces = {"application/json"})
    @CrossOrigin
    public ResponseEntity<SimulationRS> startSimulation(@Valid @RequestBody SimulationRQ simulationRQ) {
        configurationManager.overrideConfiguration(simulationRQ);
        List<Person> simulationPopulation = startup.buildSimulationData();
        simulation.runSimulation(simulationPopulation);
        SimulationRS result = getSimulationRS();
        simulationDataManager.removeSimulation();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private SimulationRS getSimulationRS() {
        SimulationData simulationData = simulationDataManager.getCurrentSimulation();
        StartConfig startConfig = configurationManager.getStartConfig();
        SimulationConfig simulationConfig = configurationManager.getSimulationConfig();
        ContagionConfig contagionConfig = configurationManager.getContagionConfig();
        ScheduleConfig scheduleConfig = configurationManager.getScheduleConfig();
        return SimulationRS.builder()
                .resultSummary(ResultSummary.builder()
                        .startTime(simulationData.getStartTime())
                        .simulationTime(simulationData.getSimulationTime())
                        .totalInfected(simulationData.getTotalInfected())
                        .totalSimulatedDays(simulationData.getTotalSimulatedDays())
                        .build())
                .hourlyReport(simulationData.getHourlyReport())
                .configuration(
                        SimulationConfiguration.builder()
                                .population(startConfig.getPopulation())
                                .personPerHouse(startConfig.getPersonPerHouse())
                                .personPerWorkplace(startConfig.getPersonPerWorkplace())
                                .initialInfectedPopulation(startConfig.getInitialInfectedPopulation())
                                .totalSimulatedHours(simulationConfig.getTotalSimulatedHours())
                                .houseSpreadChance(contagionConfig.getHouseSpreadChance())
                                .schedule(scheduleConfig.getScheduleEntries())

                                .build()
                )
                .build();
    }
}
