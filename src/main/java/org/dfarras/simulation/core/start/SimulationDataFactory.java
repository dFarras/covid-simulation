package org.dfarras.simulation.core.start;

import org.dfarras.simulation.core.data.SimulationData;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SimulationDataFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public SimulationData getNewSimulationData() {
        return applicationContext.getBean(SimulationData.class);
    }
}
