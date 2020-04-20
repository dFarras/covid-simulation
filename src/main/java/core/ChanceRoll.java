package core;

import java.util.Random;

public class ChanceRoll {
    private Random random = new Random();

    public boolean percentRoll(Double chance) {
        return (random.nextDouble() * 100) < chance;
    }
}
