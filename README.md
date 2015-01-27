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

### Function composition
```java
final Noise basicNoise = generator.generate();

Noise compoundNoise = new Noise() {
  @Override
  public float get(float x, float y) {
    return
      Math.abs(basicNoise.get(x,y)) +
      Math.abs(1/2f*basicNoise.get(x/2f, y/2f)) +
      Math.abs(1/4f*basicNoise.get(x/4f, y/4f)) +
      Math.abs(1/8f*basicNoise.get(x/8f, y/8f));
  }
};

float noiseValue = compoundNoise.get(20.5f,31.2f);
```
