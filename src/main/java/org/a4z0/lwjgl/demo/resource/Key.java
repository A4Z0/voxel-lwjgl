package org.a4z0.lwjgl.demo.resource;

public class Key {

    public static final char NAMESPACE_SEPARATOR = ':';
    public static final String DEFAULT_NAMESPACE = "default";

    protected final String n;
    protected final String p;

    /**
    * Construct a {@link Key}.
    *
    * @param p Path.
    */

    public Key(String p) {
        this(DEFAULT_NAMESPACE, p);
    }

    /**
    * Construct a {@link Key}.
    *
    * @param n Namespace.
    * @param p Path.
    */

    public Key(String n, String p) {
        this.n = n;
        this.p = p;
    }

    /**
    * @return the Namespace.
    */

    public String getNamespace() {
        return this.n;
    }

    /**
    * @return the Path.
    */

    public String getPath() {
        return this.p;
    }

    /**
    * Checks if this {@link Key} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof Key) && ((Key) o).n.equals(this.n) && ((Key) o).p.equals(this.p);
    }

    /**
    * @return this {@link Key} as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": { Namespace: \"" + this.getNamespace() + "\", Path: \"" + this.getPath() + "\" }";
    }
}