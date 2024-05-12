package org.a4z0.lwjgl.demo.voxel.block;

import org.a4z0.lwjgl.demo.voxel.math.AABB;
import org.a4z0.lwjgl.demo.voxel.math.BlockPosition;

public class BlockState {

    public static final AABB AABB = new AABB(0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f);

    protected final BlockPosition p;
    protected int i;

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public BlockState(int x, int y, int z, int r, int g, int b) {
        this(x, y, z, r, g, b, 255);
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public BlockState(int x, int y, int z, int r, int g, int b, int a) {
        this(x, y, z, (byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public BlockState(int x, int y, int z, byte r, byte g, byte b) {
        this(x, y, z, r, g, b, (byte) 255);
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public BlockState(int x, int y, int z, byte r, byte g, byte b, byte a) {
        this(x, y, z, ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF));
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param i Color.
    */

    public BlockState(int x, int y, int z, int i) {
        this(new BlockPosition(x, y, z), i);
    }

    /**
    * Construct a {@link BlockState}.
    *
    * @param p Position.
    * @param i Color.
    */

    public BlockState(BlockPosition p, int i) {
        this.p = p;
        this.i = i;
    }

    /**
    * @return the Position.
    */

    public BlockPosition getPosition() {
        return this.p;
    }

    /**
    * @return the Color.
    */

    public int getColor() {
        return this.i;
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public void setColor(int r, int g, int b) {
        this.setColor(r, g, b, 255);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public void setColor(int r, int g, int b, int a) {
        this.setColor((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public void setColor(byte r, byte g, byte b) {
        this.setColor(r, g, b, (byte) 255);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public void setColor(byte r, byte g, byte b, byte a) {
        this.setColor(((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF));
    }

    /**
    * Sets the Color.
    *
    * @param i Color.
    */

    public void setColor(int i) {
        this.i = i;
    }
}