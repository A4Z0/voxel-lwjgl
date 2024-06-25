package org.a4z0.lwjgl.demo.nbt;

public class NBTTagFloat implements NBTTagNumber<Float> {

    protected final float f;

    /**
    * Construct a {@link NBTTagFloat}.
    *
    * @param f Value to be stored.
    */

    public NBTTagFloat(float f) {
        this.f = f;
    }
}