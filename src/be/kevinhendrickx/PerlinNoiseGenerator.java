package be.kevinhendrickx;

import java.util.Random;

/**
 * Created by kevin on 1/27/15.
 */
public class PerlinNoiseGenerator {
    public PerlinNoise generate() {
        return new PerlinNoise(new GradientGrid(new Random()));
    }
}
