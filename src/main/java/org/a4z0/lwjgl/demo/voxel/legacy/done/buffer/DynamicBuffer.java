package org.a4z0.lwjgl.demo.voxel.legacy.done.buffer;

import java.util.Arrays;

public class DynamicBuffer {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    protected int i = 0;
    protected float[] x;

    /**
    * Construct a {@link DynamicBuffer}.
    */

    public DynamicBuffer() {
        this(DEFAULT_BUFFER_SIZE);
    }

    /**
    * Construct a {@link DynamicBuffer}.
    *
    * @param i Buffer size.
    */

    public DynamicBuffer(int i) {
        this.x = new float[i];
    }

    /**
    * Adds a byte to this {@link DynamicBuffer}.
    *
    * @param b Value to be added.
    *
    * @return this {@link DynamicBuffer}.
    */

    public DynamicBuffer put(byte b) {
        return this.put((float) b);
    }

    /**
    * Adds a short to this {@link DynamicBuffer}.
    *
    * @param s Value to be added.
    *
    * @return this {@link DynamicBuffer}.
    */

    public DynamicBuffer put(short s) {
        return this.put((float) s);
    }

    /**
    * Adds an int to this {@link DynamicBuffer}.
    *
    * @param i Value to be added.
    *
    * @return this {@link DynamicBuffer}.
    */

    public DynamicBuffer put(int i) {
        return this.put((float) i);
    }

    /**
    * Adds a float to this {@link DynamicBuffer}.
    *
    * @param f Value to be added.
    *
    * @return this {@link DynamicBuffer}.
    */

    public DynamicBuffer put(float f) {
        if(this.i + 1 > this.x.length)
            this.x = Arrays.copyOf(this.x, this.x.length * 2 + 1);

        this.x[this.i++] = f;

        return this;
    }

    /**
    * @return the number of elements in this {@link DynamicBuffer}.
    */

    public int size() {
        return this.i;
    }

    /**
    * Flushes this {@link DynamicBuffer}.
    */

    public void flush() {
        this.x = new float[(this.i = 0)];
    }

    /**
    * @return this {@link DynamicBuffer} as an array.
    */

    public float[] array() {
        return Arrays.copyOf(this.x, this.i);
    }
}