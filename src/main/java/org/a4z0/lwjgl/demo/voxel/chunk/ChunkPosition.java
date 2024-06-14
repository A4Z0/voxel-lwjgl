package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.math.Position3ic;

public class ChunkPosition implements Position3ic {

    public static final int MAX_VALUE = 2097152;

    protected final int x, y, z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ChunkPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int getX() {
        return this.x << 8;
    }

    @Override
    public int getY() {
        return this.y << 8;
    }

    @Override
    public int getZ() {
        return this.z << 8;
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
}