package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.math.Position3ic;

public class ChunkPosition implements Position3ic {

    protected final int x, y, z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ChunkPosition(int x, int y, int z) {
        this.x = (x >> 8);
        this.y = (y >> 8);
        this.z = (z >> 8);
    }

    @Override
    public int getX() {
        return this.x * Chunk.CHUNK_SIZE_X;
    }

    @Override
    public int getY() {
        return this.y * Chunk.CHUNK_SIZE_Y;
    }

    @Override
    public int getZ() {
        return this.z * Chunk.CHUNK_SIZE_Z;
    }

    /**
    * @return the Relative X-Axis.
    */

    public long getRelativeX() {
        return this.x & 0x1FFFFF;
    }

    /**
    * @return the Relative Y-Axis.
    */

    public long getRelativeY() {
        return this.y & 0x1FFFFF;
    }

    /**
    * @return the Relative Z-Axis.
    */

    public long getRelativeZ() {
        return this.z & 0x1FFFFF;
    }

    /**
    * @return the Index of this {@link ChunkPosition}.
    */

    public long getIndex() {
        return this.getRelativeX() | this.getRelativeY() << 21 | this.getRelativeZ() << 42;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ChunkPosition) && ((ChunkPosition) o).x == this.x && ((ChunkPosition) o).y == this.y && ((ChunkPosition) o).z == this.z;
    }

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    /*public static long getIndex(int x, int z) {
        return getIndex(x, z, Mode.CHUNK);
    }*/

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    * @param mode ...
    *
    * @return ...
    */

    /*public static long getIndex(int x, int z, Mode mode) {
        return switch (mode) {
            case CHUNK ->
                (long) x & 4294967295L | ((long) z & 4294967295L) << 32;
            case BLOCK ->
                ChunkPosition.getIndex(x >> CHUNK_SIZE_X_BIT_SHIFT, z >> CHUNK_SIZE_Z_BIT_SHIFT, Mode.CHUNK);
        };
    }*/

    /**
    * ...
    */

    /*public enum Mode {
        CHUNK,
        BLOCK;
    }*/
}