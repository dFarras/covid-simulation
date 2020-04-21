package org.dfarras.simulation.web;

import org.dfarras.simulation.configuration.ConfigurationManager;
import org.dfarras.simulation.core.Simulation;
import org.dfarras.simulation.core.data.SimulationDataManager;
import org.dfarras.simulation.core.start.Startup;
import org.dfarras.simulation.elements.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * Endpoint to start a simulation a contractor
     *
     * @return contractor with given nif
     */
    @PostMapping(path = "/start-simulation",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<SimulationRS> startSimulation(@RequestBody SimulationRQ simulationRQ) {
        configurationManager.overrideConfiguration(simulationRQ);
        List<Person> simulationPopulation = startup.buildSimulationData();
        simulation.runSimulation(simulationPopulation);
        return new ResponseEntity<>(getSimulationRS(), HttpStatus.OK);
    }

    private SimulationRS getSimulationRS() {
        return SimulationRS.builder()
                .startConfig(configurationManager.getStartConfig())
                .simulationConfig(configurationManager.getSimulationConfig())
                .contagionConfig(configurationManager.getContagionConfig())
                .simulationData(simulationDataManager.getCurrentSimulation())
                .build();
    }
}
