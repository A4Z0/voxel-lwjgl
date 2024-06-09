package org.a4z0.lwjgl.demo.voxel.block;

import org.a4z0.lwjgl.demo.voxel.math.Position3ic;

public class BlockPosition implements Position3ic {

    protected int x, y, z;

    /**
    * Construct a {@link BlockPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getZ() {
        return this.z;
    }

    /**
    * @return the Relative X-Axis.
    */

    public int getRelativeX() {
        return this.getX() & 15;
    }

    /**
    * @return the Relative Y-Axis.
    */

    public int getRelativeY() {
        return this.getY() & 127;
    }

    /**
    * @return the Relative Z-Axis.
    */

    public int getRelativeZ() {
        return this.getZ() & 15;
    }

    /**
    * @return the Index of this {@link BlockPosition}.
    */

    public int getIndex() {
        return this.getRelativeY() << 4 | this.getRelativeZ() << 4 | this.getRelativeX();
    }
}