package org.a4z0.lwjgl.demo.voxel.nbt;

public class NBTTagString implements NBTBase<String> {

    protected final String s;

    /**
    * Construct a {@link NBTTagString}.
    *
    * @param s Value to be stored.
    */

    public NBTTagString(String s) {
        this.s = s;
    }
}