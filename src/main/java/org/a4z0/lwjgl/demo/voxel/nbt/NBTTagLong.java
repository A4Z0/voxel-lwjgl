package org.a4z0.lwjgl.demo.voxel.nbt;

public class NBTTagLong implements NBTTagNumber<Integer> {

    protected final long l;

    /**
    * Construct a {@link NBTTagLong}.
    *
    * @param l Value to be stored.
    */

    public NBTTagLong(long l) {
        this.l = l;
    }
}