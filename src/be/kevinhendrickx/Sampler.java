package be.kevinhendrickx;

/**
 * Created by kevin on 1/27/15.
 */
public class Sampler {

    public float[][] sampleBySubdivisions(Noise noise, Coordinate lowerLeft, Coordinate upperRight,
                          int widthSubdivisions, int heightSubdivisions) {
        int width = 2+widthSubdivisions;
        int height = 2+heightSubdivisions;
        float[][] samples = new float[width][height];

        for(int x=0;x<width;x++) {
            for(int y=0;y<height;y++) {
                float value = noise.get(
                        lowerLeft.x+x*(upperRight.x-lowerLeft.x)/(width-1f),
                        lowerLeft.y+y*(upperRight.y-lowerLeft.y)/(height-1f));
                samples[x][y] = value;
            }
        }
        return samples;
    }

    public float[][] sample(Noise noise, Coordinate lowerLeft, Coordinate upperRight,
                            int xSamples, int ySamples) {
        return sampleBySubdivisions(noise,lowerLeft,upperRight,xSamples-2,ySamples-2);
    }
}
