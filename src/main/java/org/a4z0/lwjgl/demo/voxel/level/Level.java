package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.block.BlockState;
import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;

/**
* Represents a Level.
*/

public interface Level {

    /**
    * @return the {@link Level}'s Seed.
    */

    long getSeed();

    /**
    * Retrieves a {@link Chunk} at the coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int z);

    /**
    * Retrieves a {@link BlockState} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link BlockState}.
    */

    default BlockState getBlockAt(int x, int y, int z) {
        return this.getChunkAt(x, z).getBlockAt(x, y, z);
    }
}