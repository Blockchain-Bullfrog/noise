# noise
Perlin noise function generator

Most implementations of the perlin noise algorithm are bounded by width and height. This one however is not. The generator returns an object representation of the 
perlin noise function f:R^2 -> [-1,1]. 

In theory the noise function is unbounded. However, the randomly generated gradients for a given gridpoint must be the same with every lookup and are stored in memory. Therefore the noise function is only bounded by memory.

So far the noise function only supports 2 dimensions

## usage

### generating a noise function
```java
PerlinNoiseGenerator generator = new PerlinNoiseGenerator(); 
Noise noise1 = generator.generate();
Noise noise2 = generator.generate();
Noise noise3 = generator.generate();
```
### noise value at a coordinate
```java
float noiseValue = noise1.get(20.5f,31.2f);
```
#### Clouds
The cloud is a convergent series. The second parameter in the Cloud constructor denotes the amount of terms "n".
```java
Noise perlinNoise = generator.generate();
Noise cloud = new Cloud(perlinNoise,5);
```
![Compound 1](https://github.com/sonsyphon/noise/blob/master/docs/compound2.png)

### Function composition
The cloud is a particular function composition. It is possible to build your own compositions. To remain consistent with the code you can implement to the Noise interface.
```java
final Noise perlinNoise = generator.generate();

Noise compoundNoise = new Noise() {
  @Override
  public float get(float x, float y) {
    return
      Math.abs(perlinNoise.get(x,y)) +
      Math.abs(1/2f*perlinNoise.get(x/2f, y/2f)) +
      Math.abs(1/4f*perlinNoise.get(x/4f, y/4f)) +
      Math.abs(1/8f*perlinNoise.get(x/8f, y/8f));
  }
};
```
![Compound 1](https://github.com/sonsyphon/noise/blob/master/docs/compound1.png)
```java
final Noise perlinNoise = generator.generate();
Noise compoundNoise = new Noise() {
  @Override
  public float get(float x, float y) {
    return Math.round(perlinNoise.get(x,y)*5)/5f;
  }
};
```
![Compound 1](https://github.com/sonsyphon/noise/blob/master/docs/compound3.png)

### Sampling
The noise signal is a seemingly continuous signal, only discretized by the limitations of floating points.
To display the generated signal or to use it for e.g. a height map for terrain generation we need a discrete amount of points. In the field of Signal Processing these points are called samples.
Sampling is the process of transforming a continuous signal into a discrete signal by use of a sampler.

The image below represents a a continuous cloud function from Coordinate (0,0) to (20,20). And we would like to sample the region from (0,0) to (5,5).
![Compound 1](https://github.com/sonsyphon/noise/blob/master/docs/sampler.png)

When we want to display this region in a window of 500 by 500 pixels we need 500 samples in the x direction and 500 samples in the y direction. The Sampler will then return a 500x500 array of samples.
This is assuming that we want to draw a sample per pixel. It is possible to use less samples. when we use 250 samples in each direction this is the same as creating an image of 250x250pixels and scaling it up be a factor of 2.
```java
PerlinNoiseGenerator generator = new PerlinNoiseGenerator();
Noise perlinNoise = generator.generate();
Noise cloud = new Cloud(perlinNoise,5);
Sampler sampler = new Sampler();

Coordinate lowerLeftCoordinate = new Coordinate(0,0);
Coordinate upperRightCoordinate = new Coordinate(5,5);
int xSamples = 500;
int ySamples = 500;
float[][] samples = sampler.sample(cloud,lowerLeftCoordinate,upperRightCoordinate,xSamples,ySamples);
