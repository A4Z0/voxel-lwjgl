package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.gl.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

/**
* Represents a Level.
*/

public interface LevelAccess {

    /**
    * @return ...
    */

    long getSeed();

    /**
    * ...
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int z);

    /**
    * ...
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
    * ...
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
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param voxel {@link Voxel} to be set.
    */

    default void setVoxel(int x, int y, int z, Voxel voxel) {
        this.getChunkAt(x, z).setVoxel(x, y, z, voxel);
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    default Voxel getVoxel(int x, int y, int z) {
        return this.getChunkAt(x, z).getVoxel(x, y, z);
    }
}