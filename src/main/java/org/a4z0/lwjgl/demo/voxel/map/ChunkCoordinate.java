package org.a4z0.lwjgl.demo.voxel.map;

import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;

public class ChunkCoordinate {

    public static final long BINARY_MASK_X = 4194303L;
    public static final long BINARY_MASK_Y = 1048575L;
    public static final long BINARY_MASK_Z = 4194303L;

    public static final int Y_OFFSET = 0;
    public static final int Z_OFFSET = 20;
    public static final int X_OFFSET = 42;

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public static long asLong(int x, int y, int z) {
        long l = 0L;

        l |= ((long) x & BINARY_MASK_X) << X_OFFSET;
        l |= ((long) y & BINARY_MASK_Y) << Y_OFFSET;
        l |= ((long) z & BINARY_MASK_Z) << Z_OFFSET;

        return l;
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public static long asLongFromBlock(int x, int y, int z) {
        return asLong(x >> Chunk.CHUNK_SIZE_X_SQRT, y >> Chunk.CHUNK_SIZE_Y_SQRT, z >> Chunk.CHUNK_SIZE_Z_SQRT);
    }
}