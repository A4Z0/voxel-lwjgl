package org.a4z0.lwjgl.demo.voxel.gl.vertex;

/**
* Represents a Vertex Stream.
* Handles a continuous Flux of Vertex Elements.
*/

public interface IVertexStream extends Cloneable {

    /**
    * Adds a byte to this {@link IVertexStream}.
    *
    * @param b Value to be added.
    *
    * @return this {@link IVertexStream}.
    */

    IVertexStream put(byte b);

    /**
    * Adds a short to this {@link IVertexStream}.
    *
    * @param s Value to be added.
    *
    * @return this {@link IVertexStream}.
    */

    IVertexStream put(short s);

    /**
    * Adds an int to this {@link IVertexStream}.
    *
    * @param i Value to be added.
    *
    * @return this {@link IVertexStream}.
    */

    IVertexStream put(int i);

    /**
    * Adds a float to this {@link IVertexStream}.
    *
    * @param f Value to be added.
    *
    * @return this {@link IVertexStream}.
    */

    IVertexStream put(float f);

    /**
    * @return the number of elements in this {@link IVertexStream}.
    */

    int size();

    /**
    * Flushes this {@link IVertexStream}.
    */

    void flush();

    /**
    * @return this {@link IVertexStream} as an array.
    */

    Object array();

    /**
    * @return a clone of this {@link IVertexStream}.
    */

    IVertexStream clone();
}