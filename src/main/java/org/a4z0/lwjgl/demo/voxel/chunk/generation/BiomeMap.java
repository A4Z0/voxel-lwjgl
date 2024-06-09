package org.a4z0.lwjgl.demo.voxel.chunk.generation;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import personthecat.fastnoise.FastNoise;
import personthecat.fastnoise.data.FractalType;
import personthecat.fastnoise.data.NoiseType;

public class BiomeMap {

    protected static final FastNoise TEMPERATURE;
    protected static final float TEMPERATURE_MULTIPLIER = 0f;

    protected static final FastNoise HUMIDITY;
    protected static final float HUMIDITY_MULTIPLIER = 0f;

    protected static final FastNoise CONTINENTALNESS;
    protected static final float CONTINENTALNESS_MULTIPLIER = 0f;

    protected static final FastNoise EROSION;
    protected static final float EROSION_MULTIPLIER = 1f;

    protected static final FastNoise WEIRDNESS;
    protected static final float WEIRDNESS_MULTIPLIER = 0f;

    protected static final float PEAKS_AND_VALLEYS_MULTIPLIER = 0f;

    static {
        int Seed = 5;

        TEMPERATURE = FastNoise.createDescriptor()
            .noise(NoiseType.SIMPLEX2)
            .fractal(FractalType.FBM)
            .gain(0.5f)
            .octaves(3)
            .lacunarity(1.5f)
            .frequency(0.005f)
            .seed(Seed)
            .generate();

        HUMIDITY = FastNoise.createDescriptor()
            .noise(NoiseType.SIMPLEX2)
            .fractal(FractalType.FBM)
            .gain(0.5f)
            .octaves(3)
            .lacunarity(0.45f)
            .frequency(0.02f)
            .seed(Seed)
            .generate();

        CONTINENTALNESS = FastNoise.createDescriptor()
            .noise(NoiseType.SIMPLEX2)
            .fractal(FractalType.FBM)
            .gain(0.1f)
            .octaves(3)
            .lacunarity(2f)
            .frequency(0.01f)
            .seed(Seed)
            .generate();
        EROSION = FastNoise.createDescriptor()
            .noise(NoiseType.SIMPLEX2)
            .fractal(FractalType.FBM)
            .gain(0.1f)
            .octaves(1)
            .lacunarity(0.1f)
            .frequency(0.0001f)
            .seed(Seed)
            .generate();
        WEIRDNESS = FastNoise.createDescriptor()
            .noise(NoiseType.SIMPLEX2)
            .fractal(FractalType.FBM)
            .gain(0.1f)
            .octaves(2)
            .lacunarity(1f)
            .frequency(0.05f)
            .seed(Seed)
            .generate();
    }

    protected final int[][][] TERRAIN = new int[256][256][256];

    protected final Chunk chunk;

    public BiomeMap(Chunk chunk) {
        this.chunk = chunk;

        for(int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                for (int z = 0; z < 256; z++) {
                    int nX = Math.abs(x + chunk.getX());
                    int nZ = Math.abs(z + chunk.getZ());

                    TERRAIN[x][y][z] = (int) depth(nX, nZ) * (70 - 64) + 64;
                }
            }
        }
    }

    public int getTerrainValue(int x, int y, int z) {
        return this.TERRAIN[x][y][z];
    }

    public float depth(int x, int z) {
        return ((CONTINENTALNESS.getNoise(x, z) + CONTINENTALNESS_MULTIPLIER) + (EROSION.getNoise(x, z) + EROSION_MULTIPLIER) + (WEIRDNESS.getNoise(x, z) + WEIRDNESS_MULTIPLIER));
    }

    public float biome(int x, int y, int z) {
        return ((TEMPERATURE.getNoise(x, y, z) + TEMPERATURE_MULTIPLIER) + (HUMIDITY.getNoise(x, y, z) + HUMIDITY_MULTIPLIER) + (CONTINENTALNESS.getNoise(x, y, z) + CONTINENTALNESS_MULTIPLIER) + (EROSION.getNoise(x, y, z) + EROSION_MULTIPLIER) + (WEIRDNESS.getNoise(x, y, z) + WEIRDNESS_MULTIPLIER));
    }

    public float peaksAndVallyes(int x, int z) {
        return (WEIRDNESS.getNoise(x, z) * WEIRDNESS_MULTIPLIER) * PEAKS_AND_VALLEYS_MULTIPLIER;
    }

    public float height(int x, int y, int z) {
        return biome(x, y, z) + depth(x, z) + peaksAndVallyes(x, z);
    }
}