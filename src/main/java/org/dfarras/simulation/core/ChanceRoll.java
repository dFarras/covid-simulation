package org.dfarras.simulation.core;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ChanceRoll {
    private Random random = new Random();

    public boolean percentRoll(Double chance) {
        return (random.nextDouble() * 100) < chance;
    }
}
