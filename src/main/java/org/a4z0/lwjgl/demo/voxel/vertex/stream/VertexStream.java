package org.a4z0.lwjgl.demo.voxel.vertex.stream;

import java.util.Arrays;

/**
* Represents a {@link VertexStream}.
*/

public class VertexStream implements Stream<float[]> {

    public static final int GROWTH_FACTOR = 6;

    protected int size = 0;

    protected float[] values;

    /**
    * Construct a {@link VertexStream}.
    */

    public VertexStream() {
        this.values = new float[0];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void flush() {
        this.values = new float[0];
        this.size = 0;
    }

    @Override
    public float[] values() {
        return Arrays.copyOf(this.values, this.size);
    }

    @Override
    public VertexStream put(byte b) {
        if(this.size + GROWTH_FACTOR > this.values.length)
            this.values = Arrays.copyOf(this.values, this.values.length * 2 + GROWTH_FACTOR);

        this.values[this.size++] = b;

        return this;
    }

    @Override
    public VertexStream put(short s) {
        if(this.size + GROWTH_FACTOR > this.values.length)
            this.values = Arrays.copyOf(this.values, this.values.length * 2 + GROWTH_FACTOR);

        this.values[this.size++] = s;

        return this;
    }

    @Override
    public VertexStream put(int i) {
        if(this.size + GROWTH_FACTOR > this.values.length)
            this.values = Arrays.copyOf(this.values, this.values.length * 2 + GROWTH_FACTOR);

        this.values[this.size++] = i;

        return this;
    }

    @Override
    public VertexStream put(float f) {
        if(this.size + GROWTH_FACTOR > this.values.length)
            this.values = Arrays.copyOf(this.values, this.values.length * 2 + GROWTH_FACTOR);

        this.values[this.size++] = f;

        return this;
    }
}