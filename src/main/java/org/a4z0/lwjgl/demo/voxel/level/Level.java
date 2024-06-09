package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.block.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

import java.util.UUID;

public abstract class Level {

    protected final World w;
    protected final UUID u;
    protected final String n;
    protected final long s;

    /**
    * Construct a {@link Level}.
    *
    * @param world {@link World} this {@link Level} is in.
    * @param uuid {@link Level}'s {@link UUID}.
    * @param name {@link Level}'s Name.
    * @param seed {@link Level}'s Seed.
    */

    public Level(World world, UUID uuid, String name, long seed) {
        this.w = world;
        this.u = uuid;
        this.n = name;
        this.s = seed;
    }

    /**
    * @return ...
    */

    public World getWorld() {
        return this.w;
    }

    /**
    * @return the {@link Level}'s Seed.
    */

    public long getSeed() {
        return this.s;
    }

    /**
    * @return the {@link Level}'s UUID.
    */

    public UUID getUUID() {
        return this.u;
    }

    /**
    * @return the {@link Level}'s Name.
    */

    public String getName() {
        return this.n;
    }

    /**
    * Retrieves a {@link Chunk} at the coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    public abstract Chunk getChunkAt(int x, int y, int z);

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    public Voxel getVoxelAt(int x, int y, int z) {
        return this.getChunkAt(x, y, z).getVoxelAt(x, y, z);
    }

    /**
    * Ticks this {@link Level}.
    */

    public abstract void tick();

    /**
    * Saves this {@link Level}.
    */

    public abstract void save();
}