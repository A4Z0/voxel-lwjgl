package org.a4z0.lwjgl.demo.voxel.generation;

import org.a4z0.lwjgl.demo.voxel.noise.OpenSimplex2;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;

import java.util.Random;

/**
* ...
*/

public class OverworldChunkGenerator implements ChunkGenerator {

    public static final int SMOOTHNESS = 250;

    public static final int ORES = 1;
    public static final int ORE_SIZE = 8;
    public static final int ORE_TYPE_AREA = 400;
    public static final float ORE_CHANCE = 0.05f;

    public static final int MAX_HEIGHT = 74;
    public static final int MIN_HEIGHT = 64;

    protected final Chunk CHUNK;
    protected final Random SEED_GENERATOR;

    protected final long ORE_SEED;
    protected final long BEDROCK_SEED;

    protected final int[] surfaceMap = new int[Chunk.CHUNK_SIZE_X * Chunk.CHUNK_SIZE_Z];
    protected final int[] oreMap = new int[Chunk.CHUNK_SIZE_X * Chunk.CHUNK_SIZE_Z];

    /**
    * Construct a {@link OverworldChunkGenerator}.
    *
    * @param chunk ...
    */

    public OverworldChunkGenerator(Chunk chunk) {
        this.CHUNK = chunk;
        this.SEED_GENERATOR = new Random(chunk.getWorld().getSeed());

        this.ORE_SEED = this.SEED_GENERATOR.nextLong();
        this.BEDROCK_SEED = this.SEED_GENERATOR.nextLong();
    }

    @Override
    public void generate() {
        generateSurface();
        generateOreMap();

        for(int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for (int y = 0; y < Chunk.CHUNK_SIZE_Y; y++) {
                for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                    generateSoil(x + this.CHUNK.getX(), y, z + this.CHUNK.getZ());
                    generateBedrock(x + this.CHUNK.getX(), y, z + this.CHUNK.getZ());
                    generateOres(x + this.CHUNK.getX(), y, z + this.CHUNK.getZ());
                }
            }
        }
    }

    private void generateSurface() {
        long seed = this.CHUNK.getWorld().getSeed();

        for (int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                float value = OpenSimplex2.noise2(
                        seed,
                        ((x + 0.5) + (Math.abs(this.CHUNK.getX())) * Chunk.CHUNK_SIZE_X) / SMOOTHNESS,
                        ((z - 0.5) + (Math.abs(this.CHUNK.getZ())) * Chunk.CHUNK_SIZE_Z) / SMOOTHNESS
                );

                value = (value + 1f) * 0.5f;
                this.surfaceMap[x + (z * Chunk.CHUNK_SIZE_X)] = (int) (value * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT);
            }
        }
    }

    private void generateOreMap() {
        for (int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                float value = OpenSimplex2.noise2(this.ORE_SEED,
                        ((x + 0.5) + (Math.abs(this.CHUNK.getX())) * Chunk.CHUNK_SIZE_X) / ORE_TYPE_AREA,
                        ((-z - 0.5) + (Math.abs(this.CHUNK.getZ())) * Chunk.CHUNK_SIZE_Z) / ORE_TYPE_AREA
                );
                value = (value + 1f) * 0.5f;

                this.oreMap[x + (z * Chunk.CHUNK_SIZE_X)] = (int) Math.floor(value * ORES);
            }
        }
    }

    private void generateOres(int x, int y, int z) {
        if (y >= 10 && y <= 50) {
            float value = OpenSimplex2.noise3_ImproveXZ(this.ORE_SEED,
                    ((x + 0.5) + (Math.abs(this.CHUNK.getX())) * Chunk.CHUNK_SIZE_X) / ORE_SIZE,
                    (y + 0.5) / ORE_SIZE,
                    ((z - 0.5) + (Math.abs(this.CHUNK.getZ())) * Chunk.CHUNK_SIZE_Z) / ORE_SIZE
            );
            value = (value + 1f) * 0.5f;

            if (value > (1f - ORE_CHANCE)) {
                this.CHUNK.setVoxel(x, y, z, 50,SEED_GENERATOR.nextInt(105) + 50,SEED_GENERATOR.nextInt(55) + 200);
            }
        }
    }

    private void generateSoil(int x, int y, int z) {
        int surface = this.surfaceMap[Math.abs((x - this.CHUNK.getX())) + Math.abs(((z - this.CHUNK.getZ())) * Chunk.CHUNK_SIZE_X)];

        if (y > surface) {
            return;
        }

        int distance = (surface - y);

        if (distance < 4) {
            switch (distance) {
                case 0 ->
                        this.CHUNK.setVoxel(x, y, z, 111,SEED_GENERATOR.nextInt(25) + 150, 55);
                case 1, 2, 3 ->
                        this.CHUNK.setVoxel(x, y, z, 92, SEED_GENERATOR.nextInt(10) + 60, 50);
            }
        }
    }


    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    */

    private void generateBedrock(int x, int y, int z) {
        if (y > 3)
            return;

        double worldX = (x + 0.5) + this.CHUNK.getX() * Chunk.CHUNK_SIZE_X;
        double worldZ = (z - 0.5) + this.CHUNK.getZ() * Chunk.CHUNK_SIZE_Y;

        switch (y) {
            case 0 -> {
                this.CHUNK.setVoxel(x, y, z, 16, 16, 16);
            }
            case 1 -> {
                if(OpenSimplex2.noise2(this.BEDROCK_SEED, worldX, worldZ) > -0.5) {
                    this.CHUNK.setVoxel(x, y, z, 24, 24, 24);
                }
            }
            case 2 -> {
                if (OpenSimplex2.noise2(this.BEDROCK_SEED, worldX + 1f, worldZ - 1f) > 0) {
                    this.CHUNK.setVoxel(x, y, z, 32, 32, 32);
                }
            }
            case 3 -> {
                if (OpenSimplex2.noise2(this.BEDROCK_SEED, worldX + 2f, worldZ - 2f) > 0.5) {
                    this.CHUNK.setVoxel(x, y, z, 40, 40, 40);
                }
            }
        }
    }
}