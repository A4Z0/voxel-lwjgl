package org.a4z0.lwjgl.demo.nbt;

public class NBTTagInt implements NBTTagNumber<Integer> {

    protected final int i;

    /**
    * Construct a {@link NBTTagInt}.
    *
    * @param i Value to be stored.
    */

    public NBTTagInt(int i) {
        this.i = i;
    }
}