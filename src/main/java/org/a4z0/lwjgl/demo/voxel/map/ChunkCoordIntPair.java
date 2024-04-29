package org.a4z0.lwjgl.demo.voxel.map;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

public class ChunkCoordIntPair {

    public static final long VALUE_A = 4294967295L;
    public static final long VALUE_B = 0xffffffffL;

    public static long asLong(int x, int z) {
        return (long) x & 4294967295L | ((long) z & 4294967295L) << 32;
    }

    public static long asLongBlock(int x, int z) {
        return asLong(x >> Chunk.CHUNK_SIZE_X_BIT_SHIFT, z >> Chunk.CHUNK_SIZE_Z_BIT_SHIFT);
    }
}