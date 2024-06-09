package org.a4z0.lwjgl.demo.voxel.block;

import org.a4z0.lwjgl.demo.voxel.palette.Palette;

public class BlockState extends Palette {

    protected final BlockPosition p;

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public BlockState(int x, int y, int z) {
        this(new BlockPosition(x, y, z));
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param p Where this {@link BlockState} will be.
    */

    public BlockState(BlockPosition p) {
        super();

        this.p = p;
    }

    /**
    * @return ...
    */

    public BlockPosition getPosition() {
        return this.p;
    }
}