package core.data;

public class InfectionMediator {
    private static final InfectionObserver infectionObserver = SimulationData.getInstance();
    private static InfectionMediator infectionMediator;

    public static InfectionMediator getInfectionMediator() {
        if(infectionMediator == null) {
            infectionMediator = new InfectionMediator();
        }
        return infectionMediator;
    }

    public void signalObserver() {
        getObserver().receive();
    }

    private InfectionObserver getObserver() {
        return infectionObserver;
    }
}
