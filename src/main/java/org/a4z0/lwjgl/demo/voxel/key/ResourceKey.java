package org.a4z0.lwjgl.demo.voxel.key;

public class ResourceKey {

    public static final char NAMESPACE_SEPARATOR = ':';
    public static final String DEFAULT_NAMESPACE = "default";

    protected final String n;
    protected final String p;

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param p Path.
    */

    public ResourceKey(String p) {
        this(DEFAULT_NAMESPACE, p);
    }

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param n Namespace.
    * @param p Path.
    */

    public ResourceKey(String n, String p) {
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

    @Override
    public boolean equals(Object o) {
        return (o instanceof ResourceKey) && ((ResourceKey) o).n.equals(this.getNamespace()) && ((ResourceKey) o).p.equals(this.getPath());
    }
}