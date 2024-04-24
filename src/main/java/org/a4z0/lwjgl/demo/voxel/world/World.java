package org.a4z0.lwjgl.demo.voxel.world;

import org.a4z0.lwjgl.demo.voxel.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.world.chunk.IChunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.provider.IChunkProvider;

/**
* Represents a World.
*/

public interface World extends IChunkProvider {

    int CHUNKS_PER_DIRECTION = 4;

    /**
    * @return the Seed of this {@link World}.
    */

    long getSeed();

    /**
    * Sets a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setVoxel(int x, int y, int z, int r, int g, int b) {
        this.setVoxel(x, y, z, new Voxel(r, g, b));
    }

    /**
    * Sets a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    default void setVoxel(int x, int y, int z, int r, int g, int b, int a) {
        this.setVoxel(x, y, z, new Voxel(r, g, b, a));
    }

    /**
    * Sets a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param voxel {@link Voxel} to be set.
    */

    void setVoxel(int x, int y, int z, Voxel voxel);

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    Voxel getVoxel(int x, int y, int z);

    /**
    * @return all {@link IChunk Chunks} that are loaded in this {@link World}.
    */

    IChunk[] getChunks();
}