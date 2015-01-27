package be.kevinhendrickx.compounds;

import be.kevinhendrickx.Noise;
import be.kevinhendrickx.PerlinNoise;
import be.kevinhendrickx.PerlinNoiseGenerator;

/**
 * Created by kevin on 1/27/15.
 */
public class Cloud implements Noise {
    Noise noise;
    int n;

    public Cloud(Noise noise,int n) {
        this.noise = noise;
        this.n = n;
    }

    @Override
    public float get(float x, float y) {
        float sum = 0;
        float inc = 1.0f;

        for(int i=0;i<n;i++) {
            sum = sum + (1/inc*(noise.get(x*inc,y*inc)));
            inc = inc*2;
        }
        return sum;
    }
}
