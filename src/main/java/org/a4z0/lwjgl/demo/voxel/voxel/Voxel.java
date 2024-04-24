package org.a4z0.lwjgl.demo.voxel.voxel;

/**
* ...
*/

public class Voxel {

    public static final Voxel EMPTY_VOXEL = new Voxel(0, 0, 0, 0);

    protected final int i;

    /**
    * Construct a {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public Voxel(int r, int g, int b) {
        this(r, g, b, 255);
    }

    /**
    * Construct a {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public Voxel(int r, int g, int b, int a) {
        this((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
    * Construct a {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public Voxel(byte r, byte g, byte b) {
        this(r, g, b, (byte) 255);
    }

    /**
    * Construct a {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public Voxel(byte r, byte g, byte b, byte a) {
        this(((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF));
    }

    /**
    * Construct a {@link Voxel}.
    *
    * @param i Color of this {@link Voxel}.
    */

    public Voxel(int i) {
        this.i = i;
    }

    /**
    * @return the color of this {@link Voxel}.
    */

    public int getColor() {
        return this.i;
    }

    /**
    * Compares the color of this {@link Voxel} with another.
    *
    * @param o {@link Voxel} to be compared.
    *
    * @return true if the color is the same, false otherwise.
    */

    public boolean compare(Voxel o) {
        return this.getColor() == o.getColor();
    }
}