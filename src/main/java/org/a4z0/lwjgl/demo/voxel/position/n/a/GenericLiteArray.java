package org.a4z0.lwjgl.demo.voxel.position.n.a;

import java.lang.reflect.Array;

/**
* ...
*/

public class GenericLiteArray<V> implements LiteArray<V> {

    public static final int DEFAULT_CAPACITY = 9;
    public static final int GROWTH_FACTOR = 1;

    protected V[] ELEMENTS;
    protected int SIZE = 0;

    /**
    * Construct a {@link GenericLiteArray}.
    *
    * @param ARRAY_TYPE Type of this {@link GenericLiteArray}.
    */

    public GenericLiteArray(Class<V[]> ARRAY_TYPE) {
        this(ARRAY_TYPE, DEFAULT_CAPACITY);
    }

    /**
    * Construct a {@link GenericLiteArray}.
    *
    * @param ELEMENT_ARRAY_TYPE Type of this {@link GenericLiteArray}.
    * @param INITIAL_CAPACITY Initial capacity of this {@link GenericLiteArray}.
    */

    public GenericLiteArray(Class<V[]> ELEMENT_ARRAY_TYPE, int INITIAL_CAPACITY) {
        this.ELEMENTS = ELEMENT_ARRAY_TYPE.cast(Array.newInstance(ELEMENT_ARRAY_TYPE, INITIAL_CAPACITY));
    }

    @Override
    public int size() {
        return this.SIZE;
    }

    @Override
    public V[] values() {
        return this.ELEMENTS;
    }

    @Override
    public V get(int i) {
        return this.ELEMENTS[i];
    }

    @Override
    public void set(int i, V e) {
        this.ELEMENTS[i] = e;
    }

    @Override
    public void remove(int i) {
        this.ELEMENTS[i] = null;
    }
}