package org.a4z0.lwjgl.demo.voxel.vertex;

import java.util.Arrays;

/**
* Represents a Dynamic Vertex Stream.
* Handles a continuous Flux of Vertex Elements.
*/

public class VertexStream implements IVertexStream {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    protected int i = 0;
    protected float[] x;

    /**
    * Construct a {@link VertexStream}.
    */

    public VertexStream() {
        this(DEFAULT_BUFFER_SIZE);
    }

    /**
    * Construct a {@link VertexStream}.
    *
    * @param i Buffer size.
    */

    public VertexStream(int i) {
        this.x = new float[i];
    }

    @Override
    public VertexStream put(byte b) {
        return this.put((float) b);
    }

    @Override
    public VertexStream put(short s) {
        return this.put((float) s);
    }

    @Override
    public VertexStream put(int i) {
        return this.put((float) i);
    }

    @Override
    public VertexStream put(float f) {
        if(this.i + 1 > this.x.length)
            this.x = Arrays.copyOf(this.x, this.x.length * 2);

        this.x[this.i++] = f;

        return this;
    }

    @Override
    public int size() {
        return this.i;
    }

    @Override
    public void flush() {
        this.x = new float[(this.i = 0)];
    }

    @Override
    public float[] array() {
        return Arrays.copyOf(this.x, this.i);
    }

    @Override
    public VertexStream clone() {
        try {
            VertexStream clone = (VertexStream) super.clone();
            clone.x = this.x.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Unable to clone this Vertex Stream.", e);
        }
    }
}