package org.a4z0.lwjgl.demo.nbt;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NBTTagList implements Iterable<NBTBase<?>> {

    public static final int GROWTH_FACTOR = 2;

    protected int i;
    protected NBTBase<?>[] eA;

    /**
    * Construct a {@link NBTTagList}.
    */

    public NBTTagList() {
        this.eA = new NBTBase<?>[this.i = 0];
    }

    /**
    * @return the number of elements in this {@link NBTTagList}.
    */

    public int size() {
        return this.i;
    }

    /**
    * @return true if this {@link NBTTagList} is empty, false otherwise.
    */

    public boolean isEmpty() {
        return this.i == 0;
    }

    /**
    * Retrieves a {@link NBTBase} at the Index.
    *
    * @param i Index where the {@link NBTBase} is.
    *
    * @return a {@link NBTBase} if it exists, null otherwise.
    */

    public NBTBase<?> get(int i) {
        if(i < 0 || i > this.eA.length)
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds for length " + this.eA.length + " in this " + this.getClass().getSimpleName() + ".");

        return this.eA[i];
    }

    /**
    * Adds a {@link NBTBase}.
    *
    * @param e {@link NBTBase} to be added.
    */

    public void add(NBTBase<?> e) {
        if(this.eA.length < (this.i + 1))
            this.eA = Arrays.copyOf(this.eA, (this.eA.length + GROWTH_FACTOR));

        this.set(i++, e);
    }

    /**
    * Sets a {@link NBTBase}.
    *
    * @param i Index where the {@link NBTBase} will be set.
    * @param e {@link NBTBase} to be set.
    */

    public void set(int i, NBTBase<?> e) {
        if(i < 0 || i > this.eA.length)
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds for length " + this.eA.length + " in this " + this.getClass().getSimpleName() + ".");

        this.eA[i] = e;
    }

    /**
    * Removes a {@link NBTBase}.
    *
    * @param e {@link NBTBase} to be removed.
    */

    public void remove(NBTBase<?> e) {
        for(int i = 0; i < this.eA.length; i++)
            if(this.eA[i].equals(e))
                this.remove(i);
    }

    /**
    * Removes a {@link NBTBase}.
    *
    * @param i Index to be removed.
    */

    public void remove(int i) {
        if(i < 0 || i > this.eA.length)
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds for length " + this.eA.length + " in this " + this.getClass().getSimpleName() + ".");

        int n = (this.i - i - 1);

        if(n > 0) System.arraycopy(this.eA, i + 1, this.eA, i, n);

        this.eA[this.i--] = null;
    }

    /**
    * Clears this {@link NBTTagList}.
    */

    public void clear() {
        this.eA = new NBTBase<?>[this.i = 0];
    }

    /**
    * @return a {@link Iterator} of this {@link NBTTagList}.
    */

    public Iterator<NBTBase<?>> iterator() {
        return new Iterator<>() {

            private int i;

            @Override
            public boolean hasNext() {
                return (i < size());
            }

            @Override
            public NBTBase<?> next() {
                if(i >= size())
                    throw new NoSuchElementException("There are no elements left in this " + NBTTagList.class.getSimpleName() + ".");

                return get(i++);
            }
        };
    }
}