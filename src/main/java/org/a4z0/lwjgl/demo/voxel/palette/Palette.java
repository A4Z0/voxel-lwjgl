package org.a4z0.lwjgl.demo.voxel.palette;

import java.util.Arrays;

/**
* Represents a Palette.
* Sets and retrieves data through an <i>Indexing Strategy</i>.
*/

public class Palette<T> {

    protected final int LENGTH_X;
    protected final int LENGTH_Y;
    protected final int LENGTH_Z;

    protected final int INDEXED_LENGTH_X;
    protected final int INDEXED_LENGTH_Y;
    protected final int INDEXED_LENGTH_Z;

    protected final int BIT_SHIFT_X;
    protected final int BIT_SHIFT_Z;

    protected final T[] ELEMENT_ARRAY;

    /**
    * Construct a {@link Palette}.
    *
    * @param LENGTH_X Length along the X-axis.
    * @param LENGTH_Y Length along the Y-axis.
    * @param LENGTH_Z Length along the Z-axis.
    */

    public Palette(int LENGTH_X, int LENGTH_Y, int LENGTH_Z) {
        this.LENGTH_X = LENGTH_X & 0x7FFFFFFF;
        this.LENGTH_Y = LENGTH_Y & 0x7FFFFFFF;
        this.LENGTH_Z = LENGTH_Z & 0x7FFFFFFF;

        this.INDEXED_LENGTH_X = this.LENGTH_X - 1;
        this.INDEXED_LENGTH_Y = this.LENGTH_Y - 1;
        this.INDEXED_LENGTH_Z = this.LENGTH_Z - 1;

        this.BIT_SHIFT_X = (int) (Math.log(this.LENGTH_X) / Math.log(2));
        this.BIT_SHIFT_Z = (int) (Math.log(this.LENGTH_Z) / Math.log(2));

        //noinspection unchecked
        this.ELEMENT_ARRAY = (T[]) new Object[this.LENGTH_X * this.LENGTH_Y * this.LENGTH_Z];
    }

    /**
    * ...
    *
    * @param e ...
    *
    * @return ...
    */

    public Palette<T> fill(T e) {
        Arrays.fill(this.ELEMENT_ARRAY, e);

        return this;
    }

    /**
    * Retrieves an {@link T Element} in this {@link Palette} at the index.
    *
    * @param i Index.
    *
    * @return an {@link T Element} if it exists, null otherwise.
    */

    public T get(int i) {
        return this.ELEMENT_ARRAY[i];
    }

    /**
    * Retrieves an {@link T Element} in this {@link Palette} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return an {@link T Element} if it exists, null otherwise.
    */

    public T get(int x, int y, int z) {
        return this.get(((y & INDEXED_LENGTH_Y) << BIT_SHIFT_X | (z & INDEXED_LENGTH_Z)) << BIT_SHIFT_Z | x & INDEXED_LENGTH_X);
    }

    /**
    * Sets an {@link T Element} in this {@link Palette} at the index.
    *
    * @param i Index.
    * @param e {@link T Element} to be set.
    */

    public void set(int i, T e) {
        this.ELEMENT_ARRAY[i] = e;
    }

    /**
    * Sets an {@link T Element} in this {@link Palette} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param e {@link T Element} to be set.
    */

    public void set(int x, int y, int z, T e) {
        this.set(((y & INDEXED_LENGTH_Y) << BIT_SHIFT_X | (z & INDEXED_LENGTH_Z)) << BIT_SHIFT_Z | x & INDEXED_LENGTH_X, e);
    }

    /**
    * Removes an {@link T Element} in this {@link Palette} at the index.
    *
    * @param i Index.
    */

    public void remove(int i) {
        this.set(i, null);
    }

    /**
    * Removes an {@link T Element} in this {@link Palette} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public void remove(int x, int y, int z) {
        this.set(x, y, z, null);
    }
}