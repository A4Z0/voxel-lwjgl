package org.a4z0.lwjgl.demo.voxel.buffer;

import java.nio.*;

import static org.lwjgl.system.MemoryUtil.*;

public class DynamicByteBuffer implements AutoCloseable {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    protected long l;
    protected int p, c;

    /**
    * Construct a {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer() {
        this(DEFAULT_BUFFER_SIZE);
    }

    /**
    * Construct a {@link DynamicByteBuffer}.
    *
    * @param i Initial capacity.
    */

    public DynamicByteBuffer(int i) {
        this.l = nmemAlloc(this.c = (i & Integer.MAX_VALUE));
    }

    /**
    * @return the number of bytes in this {@link DynamicByteBuffer}.
    */

    public int size() {
        return this.c;
    }

    /**
    * Clears all bytes from this {@link DynamicByteBuffer}.
    */

    public void clear() {
        memSet(this.l, (this.p = 0), this.c);
    }

    /**
    * Closes this {@link DynamicByteBuffer}.
    */

    @Override
    public void close() {
        if(this.l == 0)
            return;

        nmemFree(this.l);

        this.l = 0;
    }

    /**
    * Retrieves the remaining space in this {@link DynamicByteBuffer}.
    *
    * @return the number of remaining bytes.
    */

    protected int remaining() {
        return (this.c - this.p);
    }

    /**
    * Increases the capacity of this {@link DynamicByteBuffer}.
    */

    protected void grow() {
        this.l = nmemRealloc(this.l, this.c = (2 * this.c));
    }

    /**
    * Adds a {@link Byte} in this {@link DynamicByteBuffer}.
    *
    * @param b Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(byte b) {
        if(this.remaining() < Byte.BYTES)
            this.grow();

        memPutByte(this.l + this.p, b);

        this.p += Byte.BYTES;

        return this;
    }

    /**
    * Adds a {@link Short} in this {@link DynamicByteBuffer}.
    *
    * @param s Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(short s) {
        if(this.remaining() < Short.BYTES)
            this.grow();

        memPutShort(this.l + this.p, s);

        this.p += Short.BYTES;

        return this;
    }

    /**
    * Adds a {@link Integer} in this {@link DynamicByteBuffer}.
    *
    * @param i Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(int i) {
        if(this.remaining() < Integer.BYTES)
            this.grow();

        memPutInt(this.l + this.p, i);

        this.p += Integer.BYTES;

        return this;
    }

    /**
    * Adds a {@link Float} in this {@link DynamicByteBuffer}.
    *
    * @param f Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(float f) {
       if(this.remaining() < Float.BYTES)
           this.grow();

       memPutFloat(this.l + (this.p), f);

       this.p += Float.BYTES;

       return this;
    }

    /**
    * Adds a {@link Double} in this {@link DynamicByteBuffer}.
    *
    * @param d Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(double d) {
        if(this.remaining() < Double.BYTES)
            this.grow();

        memPutDouble(this.l + (this.p), d);

        this.p += Double.BYTES;

        return this;
    }

    /**
    * Adds a {@link Long} in this {@link DynamicByteBuffer}.
    *
    * @param l Value that will be added.
    *
    * @return this {@link DynamicByteBuffer}.
    */

    public DynamicByteBuffer put(long l) {
        if(this.remaining() < Long.BYTES)
            this.grow();

        memPutLong(this.l + this.p, l);

        this.p += Long.BYTES;

        return this;
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link ByteBuffer}.
    */

    public ByteBuffer asByteBuffer() {
        return memByteBuffer(this.l, this.p);
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link ByteBuffer}.
    */

    public ShortBuffer asShortBuffer() {
        return memShortBuffer(this.l, this.p);
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link IntBuffer}.
    */

    public IntBuffer asIntBuffer() {
        return memIntBuffer(this.l, this.p);
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link FloatBuffer}.
    */

    public FloatBuffer asFloatBuffer() {
        return memFloatBuffer(this.l, this.p);
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link DoubleBuffer}.
    */

    public DoubleBuffer asDoubleBuffer() {
        return memDoubleBuffer(this.l, this.p);
    }

    /**
    * @return this {@link DynamicByteBuffer} as a new {@link LongBuffer}.
    */

    public LongBuffer asLongBuffer() {
        return memLongBuffer(this.l, this.p);
    }
}