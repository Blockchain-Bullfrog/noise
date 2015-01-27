package be.kevinhendrickx;

import java.lang.Math;

/**
 * Created by kevin on 1/26/15.
 */
public class PerlinNoise {
    GradientGrid gradientGrid;

    public PerlinNoise(GradientGrid gradientGrid) {
        this.gradientGrid = gradientGrid;
    }

    public float get(Coordinate coordinate) {
        return this.get(coordinate.x,coordinate.y);
    }

    public float get(float x, float y) {
        // get the four surrounding grid points gradients and compute the inner product
        // let P = the chosen point
        // let Q_i = one of the surrounding grid points with i from {1,..4}
        // let G_i = the gradient for Q_i
        // for each i compute the inner product G_i * (P - Q_i)
        int xFloor = (int) Math.floor(x);
        int yFloor = (int) Math.floor(y);
        int xCeil = (int) Math.ceil(x);
        int yCeil = (int) Math.ceil(y);
        Gradient upperLeftGradient = gradientGrid.get(xFloor, yCeil);
        Gradient upperRightGradient = gradientGrid.get(xCeil,yCeil);
        Gradient lowerLeftGradient = gradientGrid.get(xFloor,yFloor);
        Gradient lowerRightGradient = gradientGrid.get(xCeil,yFloor);

        float upperLeftProduct = upperLeftGradient.x * (x - xFloor) + upperLeftGradient.y * (y - yCeil);
        float upperRightProduct = upperRightGradient.x * (x - xCeil) + upperRightGradient.y * (y - yCeil);
        float lowerLeftProduct = lowerLeftGradient.x * (x - xFloor) + lowerLeftGradient.y * (y - yFloor);
        float lowerRightProduct = lowerRightGradient.x * (x - xCeil) + lowerRightGradient.y * (y - yFloor);

        // interpolate
        float dx = x - xFloor;
        float dy = y - yFloor;
        float i1 = interpolate(lowerLeftProduct,lowerRightProduct,dx);
        float i2 = interpolate(upperLeftProduct,upperRightProduct,dx);
        return interpolate(i1,i2,dy);
    }

    private float interpolate(float a0,float a1, float w) {
        double ft = w * 3.1415927;
        double f = (1 - Math.cos(ft)) * .5;
        return  (float) (a0*(1-f) + a1*f);
    }

}

