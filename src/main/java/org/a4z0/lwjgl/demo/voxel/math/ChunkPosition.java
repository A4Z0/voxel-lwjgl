package org.a4z0.lwjgl.demo.voxel.math;

import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;

/**
* ...
*/

public class ChunkPosition {

    protected final int x, z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public ChunkPosition(int x, int z) {
        this.x = (x >> Chunk.CHUNK_SIZE_X_BIT_SHIFT) * Chunk.CHUNK_SIZE_X;
        this.z = (z >> Chunk.CHUNK_SIZE_Z_BIT_SHIFT) * Chunk.CHUNK_SIZE_Z;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    public static long getIndex(int x, int z) {
        return (long) x & 4294967295L | ((long) z & 4294967295L) << 32;
    }

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    public static long getIndexFromBlock(int x, int z) {
        return getIndex(x >> Chunk.CHUNK_SIZE_X_BIT_SHIFT, z >> Chunk.CHUNK_SIZE_Z_BIT_SHIFT);
    }
}