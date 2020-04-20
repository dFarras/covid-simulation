import configuration.models.ContagionConfig;
import configuration.models.StartConfig;
import core.Simulation;
import core.data.SimulationData;
import core.start.Startup;
import elements.Person;
import elements.places.House;
import configuration.ConfigurationManager;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        Startup startup = new Startup();
        List<Person> population = startup.buildSimulationData();
        Simulation simulation = new Simulation();
        SimulationData simulationData = simulation.runSimulation(population);

        StartConfig startConfig = ConfigurationManager.getStartConfig();
        ContagionConfig contagionConfig = ConfigurationManager.getContagionConfig();
        configuration.models.SimulationConfig simulationConfig = ConfigurationManager.getSimulationConfig();
        System.out.print("------SIMULATION------\n" +
                "Configuration\n" +
                "Population -> " + startConfig.getPopulation() + " Person per house -> " + startConfig.getPersonPerHouse() + " InitialInfectedPopulation -> " + startConfig.getInitialInfectedPopulation() +  "\n" +
                "House spread chance per hour -> " + contagionConfig.getHouseSpreadChance() + "\n" +
                "Total hours simulated -> " + simulationConfig.getTotalSimulatedHours() + "\n" +
                "Simulation data\n" +
                "Started at -> " + simulationData.getStartTime() + "\n" +
                "Total days simulated -> " + simulationData.getTotalSimulatedDays()  + "\n" +
                "Total infected population -> " + simulationData.getTotalInfected());
    }
}
