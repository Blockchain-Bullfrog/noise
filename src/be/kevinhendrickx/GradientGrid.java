package be.kevinhendrickx;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by kevin Hendrickx on 1/27/15.
 */
public class GradientGrid {
    private HashMap<Coordinate,Gradient> gradients;
    private Random random;

    public GradientGrid(Random random,int initialCapacity) {
        gradients = new HashMap<Coordinate, Gradient>(initialCapacity);
        this.random = random;
    }

    public Gradient get(Coordinate coordinate) {
        Gradient gradient = gradients.get(coordinate);
        if(gradient==null) {
            gradient = generateRandom();
            gradients.put(coordinate,gradient);
        }
        return gradient;
    }

    public Gradient get(int x, int y) {
        return this.get(new Coordinate(x,y));
    }

    private Gradient generateRandom() {
        // a complete revolution on unit circle = 2pi
        // so we want a random number between 0 and 2pi
        float radian = random.nextFloat()*2*(float)Math.PI;
        // we convert polar coordinate with r=1 to cartesian
        float x = (float) Math.cos(radian);
        float y = (float) Math.sin(radian);
        return new Gradient(x,y);
    }


}
