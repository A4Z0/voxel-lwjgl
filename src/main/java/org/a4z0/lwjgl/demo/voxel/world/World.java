package org.a4z0.lwjgl.demo.voxel.world;

import java.util.UUID;

public abstract class World {

    protected final UUID u;
    protected final String n;

    /**
    * Construct a {@link World}.
    *
    * @param u ...
    * @param n ...
    */

    public World(UUID u, String n) {
        this.u = u;
        this.n = n;
    }

    /**
    * @return the {@link UUID}.
    */

    public UUID getUUID() {
        return this.u;
    }

    /**
    * @return the Name.
    */

    public String getName() {
        return this.n;
    }

    /**
    * Ticks this {@link World}.
    */

    public abstract void tick();

    /**
    * Saves this {@link World}.
    */

    public abstract void save();
}