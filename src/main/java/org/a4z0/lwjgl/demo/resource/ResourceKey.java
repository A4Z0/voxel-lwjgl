package org.a4z0.lwjgl.demo.resource;

public class ResourceKey {

    protected final Key r;
    protected final Key l;

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param r Registry.
    * @param l Location.
    */

    public ResourceKey(Key r, Key l) {
        this.r = r;
        this.l = l;
    }

    /**
    * @return the Registry {@link Key}.
    */

    public Key getRegistry() {
        return this.r;
    }

    /**
    * @return the Location {@link Key}.
    */

    public Key getLocation() {
        return this.l;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ResourceKey) && ((ResourceKey) o).getRegistry().equals(this.r) && ((ResourceKey) o).getLocation().equals(this.l);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " { "
            + "Registry: " + this.r.toString().substring(this.r.getClass().getSimpleName().length() + 1) + " }"
            + ", "
            + "Location: " + this.l.toString().substring(this.l.getClass().getSimpleName().length() + 1) + " }";
    }
}