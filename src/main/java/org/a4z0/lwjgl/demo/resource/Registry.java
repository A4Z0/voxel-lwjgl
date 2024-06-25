package org.a4z0.lwjgl.demo.resource;

import java.util.Arrays;
import java.util.Optional;

public class Registry<T>  {

    public static final int GROWTH_FACTOR = 1;

    protected ResourceKey[] kA;
    protected T[] eA;

    protected int i;

    /**
    * Construct a {@link Registry}.
    */

    public Registry() {
        this.kA = new ResourceKey[1];
        this.eA = (T[]) new Object[1];
    }

    /**
    * @return ...
    */

    public int size() {
        return this.i;
    }

    /**
    * ...
    *
    * @param k ...
    *
    * @return ...
    */

    public Optional<T> get(ResourceKey k) {
        for(int i = 0; i < this.i; i++)
            if(this.kA[i].equals(k))
                return Optional.of(this.eA[i]);

        return Optional.empty();
    }

    /**
    * ...
    *
    * @param k ...
    * @param e ...
    *
    * @return this {@link Registry}.
    */

    public Registry<T> register(ResourceKey k, T e) {
        for(int i = 0; i < this.i; i++) {
            if(this.kA[i].equals(k)) {
                this.eA[i] = e;

                return this;
            }
        }

        if(this.i + GROWTH_FACTOR > this.kA.length) {
            this.kA = Arrays.copyOf(this.kA, this.kA.length * 2 + GROWTH_FACTOR);
            this.eA = Arrays.copyOf(this.eA, this.eA.length * 2 + GROWTH_FACTOR);
        }

        this.kA[this.i] = k;
        this.eA[this.i] = e;

        this.i++;

        return this;
    }

    /**
    * ...
    *
    * @param k ...
    *
    * @return this {@link Registry}.
    */

    public Registry<T> unregister(ResourceKey k) {
        for(int i = 0; i < this.i; i++) {
            if(this.kA[i].equals(k)) {
                System.arraycopy(this.kA, i + 1, this.kA, i, this.i - i - 1);
                System.arraycopy(this.eA, i + 1, this.eA, i, this.i - i - 1);

                this.i--;

                return this;
            }
        }

        return this;
    }
}