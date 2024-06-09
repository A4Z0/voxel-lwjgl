package org.a4z0.lwjgl.demo.voxel.chunk.generation;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

public class BasicGenerator implements ChunkGenerator {

    protected final BiomeMap biome;
    protected final Chunk chunk;

    /**
    * Construct a {@link BasicGenerator}.
    *
    * @param chunk ...
    */

    public BasicGenerator(Chunk chunk) {
        this.chunk = chunk;
        this.biome = new BiomeMap(chunk);
    }

    @Override
    public void generate() {
        for(int x = 0; x < 256; x++) {
            for(int y = 0; y < 256; y++) {
                for (int z = 0; z < 256; z++) {
                    int value = this.biome.getTerrainValue(x, y, z);

                    if(y < 1 || y > 5)
                        continue;

                    int grey = y * 2 * (value / 50);
                    this.chunk.getVoxelAt(x, y, z).setColor(grey, grey, grey);
                }
            }
        }
    }
}