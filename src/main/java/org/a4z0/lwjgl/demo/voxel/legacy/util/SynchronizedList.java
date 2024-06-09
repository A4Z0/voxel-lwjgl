package org.a4z0.lwjgl.demo.voxel.legacy.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* A Synchronized ordered collection of values.
*
* @param <T> Type of value to be stored.
*/

public class SynchronizedList<T> implements Iterable<T> {

    protected int i;
    protected T[] eA;

    /**
    * Construct a {@link SynchronizedList}.
    */

    public SynchronizedList() {
        this.clear();
    }

    /**
    * @return the number of elements in this {@link SynchronizedList}.
    */

    public synchronized int size() {
        return this.i;
    }

    /**
    * @return true if this {@link SynchronizedList} is empty, false otherwise.
    */

    public synchronized boolean isEmpty() {
        for(int i = 0; i < this.i; i++)
            if(this.eA[i] != null)
                return true;

        return true;
    }

    /**
    * @return this {@link SynchronizedList} as an Array.
    */

    public T[] toArray() {
        return Arrays.copyOf(this.eA, this.i);
    }

    /**
    * Performs a Search for the Element and returns if found.
    *
    * @param e Element to be searched.
    *
    * @return true if it contains, false otherwise.
    */

    public synchronized boolean contains(T e) {
        for(int i = 0; i < this.i; i++)
            if(this.eA[i].equals(e))
                return true;

        return false;
    }

    /**
    * Retrieves an Element at the Index in this {@link SynchronizedList}.
    *
    * @param i Index where the Element is.
    *
    * @return an Element if it exists, null otherwise.
    */

    public synchronized T get(int i) {
        if(i < 0 || i >= this.i)
            throw new IndexOutOfBoundsException("Index " + i + " outside the limits of " + this.i + ".");

        return this.eA[i];
    }

    /**
    * Adds an Element in this {@link SynchronizedList}.
    *
    * @param e Element to be added.
    *
    * @return this {@link SynchronizedList}.
    */

    public synchronized SynchronizedList<T> add(T e) {
        if(this.eA.length < (this.i + 1))
            this.eA = Arrays.copyOf(this.eA, (2 * this.eA.length));

        this.eA[this.i++] = e;

        return this;
    }

    /**
    * Sets an Element in this {@link SynchronizedList}.
    *
    * @param i Index of the Element to set.
    * @param e Element to be set.
    *
    * @return this {@link SynchronizedList}.
    */

    public synchronized SynchronizedList<T> set(int i, T e) {
        if(i < 0 || i >= this.i)
            throw new IndexOutOfBoundsException("Index " + i + " outside the limits of " + this.i + ".");

        this.eA[i] = e;

        return this;
    }

    /**
    * Removes an Element in this {@link SynchronizedList}.
    *
    * @param e Element to be removed.
    *
    * @return this {@link SynchronizedList}.
    */

    public synchronized SynchronizedList<T> remove(T e) {
        for(int i = 0; i < this.eA.length; i++) {
            if(this.eA[i].equals(e)) {
                int n = (this.i - i - 1);

                if(n > 0) System.arraycopy(this.eA, i + 1, this.eA, i, n);

                this.eA[this.i--] = null;

                break;
            }
        }

        return this;
    }

    /**
    * Clears this {@link SynchronizedList}.
    */

    public synchronized void clear() {

        //noinspection unchecked
        this.eA = (T[]) new Object[this.i = 1];
    }

    /**
    * @return a {@link Iterator} of this {@link SynchronizedList}.
    */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int i;

            @Override
            public boolean hasNext() {
                return (i < size());
            }

            @Override
            public T next() {
                if(i >= size())
                    throw new NoSuchElementException("There are no elements left.");

                return get(i++);
            }
        };
    }
}