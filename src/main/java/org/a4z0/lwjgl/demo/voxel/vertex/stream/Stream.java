package org.a4z0.lwjgl.demo.voxel.vertex.stream;

/**
* Represents a {@link Stream}.
*/

public interface Stream<T> {

    /**
    * @return the number of elements in this {@link Stream}.
    */

    int size();

    /**
    * Flushes this {@link Stream}.
    */

    void flush();

    /**
    * @return the {@link Stream} values.
    */

    T values();

    /**
    * Consumes the values of this {@link Stream}.
    *
    * @param consumer {@link Stream.Consumer} who will consume the values.
    */

    default void consume(Stream.Consumer<T> consumer) {
        consumer.consume(this.values());
    }

    /**
    * Adds a byte to this {@link Stream}.
    *
    * @param b ...
    *
    * @return this {@link Stream}.
    */

    Stream<T> put(byte b);

    /**
    * Adds a short to this {@link Stream}.
    *
    * @param s ...
    *
    * @return this {@link Stream}.
    */

    Stream<T> put(short s);

    /**
    * Adds an int to this {@link Stream}.
    *
    * @param i ...
    *
    * @return this {@link Stream}.
    */

    Stream<T> put(int i);

    /**
    * Adds a float to this {@link Stream}.
    *
    * @param f ...
    *
    * @return this {@link Stream}.
    */

    Stream<T> put(float f);

    /**
    * Represents a {@link Stream.Consumer}.
    */

    interface Consumer<T> {

        /**
        * Consumes the given values.
        *
        * @param e Values to be consumed.
        */

        void consume(T e);
    }
}