package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.gl.voxel.Voxel;

/**
* Represents a Chunk.
*/

public interface Chunk {

    int CHUNK_SIZE_X = 16;
    int CHUNK_SIZE_Y = 128;
    int CHUNK_SIZE_Z = 16;

    int CHUNK_REAL_SIZE_X = CHUNK_SIZE_X - 1;
    int CHUNK_REAL_SIZE_Y = CHUNK_SIZE_Y - 1;
    int CHUNK_REAL_SIZE_Z = CHUNK_SIZE_Z - 1;

    int CHUNK_SIZE_X_BIT_SHIFT = (int) (Math.log(CHUNK_SIZE_X) / Math.log(2));
    int CHUNK_SIZE_Z_BIT_SHIFT = (int) (Math.log(CHUNK_SIZE_Z) / Math.log(2));

    /**
    * @return the {@link LevelAccess} this {@link Chunk} is in.
    */

    LevelAccess getLevel();

    /**
    * @return the X-Axis of this {@link Chunk}.
    */

    int getX();

    /**
    * @return the Z-Axis of this {@link Chunk}.
    */

    int getZ();

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
    * Loads this {@link Chunk}.
    *
    * @return true if it loads correctly, false otherwise.
    */

    default boolean load() {
        return this.load(false);
    }

    /**
    * Loads this {@link Chunk}.
    *
    * @param g Generates this Chunk when true.
    *
    * @return true if it loads correctly, false otherwise.
    */

    boolean load(boolean g);

    /**
    * Unloads this {@link Chunk}.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    default boolean unload() {
        return this.unload(true);
    }

    /**
    * Unloads this {@link Chunk}.
    *
    * @param l Unloads slowly when true.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    boolean unload(boolean l);
}