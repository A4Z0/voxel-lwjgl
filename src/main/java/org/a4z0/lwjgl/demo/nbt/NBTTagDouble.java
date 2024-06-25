package org.a4z0.lwjgl.demo.nbt;

public class NBTTagDouble implements NBTTagNumber<Double> {

    protected final double d;

    /**
    * Construct a {@link NBTTagDouble}.
    *
    * @param d Value to be stored.
    */

    public NBTTagDouble(double d) {
        this.d = d;
    }
}