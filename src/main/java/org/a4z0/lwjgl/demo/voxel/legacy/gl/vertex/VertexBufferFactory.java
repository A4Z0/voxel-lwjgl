package org.a4z0.lwjgl.demo.voxel.legacy.gl.vertex;

/**
* ...
*/

public class VertexBufferFactory {

    protected float[] x;
    protected int i;

    /**
    * Construct a {@link VertexBufferFactory}.
    */

    protected VertexBufferFactory() {
        this.x = new float[0];
        this.i = 0;
    }

    /**
    * Elements that will be applied to the {@link VertexBuffer}.
    *
    * @return this {@link VertexBufferFactory}.
    */

    public VertexBufferFactory elements(float[] x) {
        this.x = x;

        return this;
    }

    /**
    * Sets the Elements per Vertex.
    *
    * @param i Elements per Vertex.
    *
    * @return this {@link VertexBufferFactory}.
    */

    public VertexBufferFactory size(int i) {
        this.i = i;

        return this;
    }

    /**
    * @return a new {@link VertexBuffer}.
    */

    public VertexBuffer build() {
        return new VertexBuffer(this.x, this.i);
    }
}