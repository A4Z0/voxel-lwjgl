package org.a4z0.lwjgl.demo.voxel.world;

import org.a4z0.lwjgl.demo.voxel.voxel.Palette;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;

/**
* Represents a World.
*/

public interface World extends Palette {

    int CHUNKS_PER_DIRECTION = 8;

    /**
    * @return the Seed of this {@link World}.
    */

    long getSeed();

    /**
    * Obtains a {@link Chunk} at the given coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int z);

    /**
    * @return all {@link Chunk Chunks} that are loaded in this {@link World}.
    */

    Chunk[] getChunks();
}