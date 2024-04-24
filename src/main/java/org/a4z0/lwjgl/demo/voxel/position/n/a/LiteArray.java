package org.a4z0.lwjgl.demo.voxel.position.n.a;

/**
* ...
*/

public interface LiteArray<V> {

    /**
    * @return the number of elements in this {@link LiteArray}.
    */

    int size();

    /**
    * @return ...
    */

    V[] values();

    /**
    * ...
    *
    * @param i ...
    *
    * @return ...
    */

    V get(int i);

    /**
    * ...
    *
    * @param i ...
    * @param e ...
    */

    void set(int i, V e);

    /**
    * ...
    *
    * @param i ...
    */

    void remove(int i);
}