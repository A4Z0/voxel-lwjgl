package org.a4z0.lwjgl.demo.voxel.nbt;

public class NBTTagByte implements NBTTagNumber<Byte> {

    protected final byte b;

    /**
    * Construct a {@link NBTTagByte}.
    *
    * @param b Value to be stored.
    */

    public NBTTagByte(byte b) {
        this.b = b;
    }
}