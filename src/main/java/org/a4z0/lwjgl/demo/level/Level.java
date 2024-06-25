package org.a4z0.lwjgl.demo.level;

import org.a4z0.lwjgl.demo.chunk.Chunk;
import org.a4z0.lwjgl.demo.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.server.Server;
import org.a4z0.lwjgl.demo.voxel.Voxel;

import java.util.UUID;

public class Level {

    protected final ChunkProvider provider;

    protected final UUID uuid;
    protected final String name;
    protected final long seed;

    /**
    * Construct a {@link Level}.
    *
    * @param uuid {@link Level}'s {@link UUID}.
    * @param name {@link Level}'s Name.
    * @param seed {@link Level}'s Seed.
    */

    public Level(Server server, UUID uuid, String name, long seed) {
        this.provider = new ChunkProvider(server, this);
        this.uuid = uuid;
        this.name = name;
        this.seed = seed;
    }

    /**
    * @return the {@link Server}.
    */

    public Server getServer() {
        return this.provider.getServer();
    }

    /**
    * @return the {@link Level}'s Seed.
    */

    public long getSeed() {
        return this.seed;
    }

    /**
    * @return the {@link Level}'s UUID.
    */

    public UUID getUUID() {
        return this.uuid;
    }

    /**
    * @return the {@link Level}'s Name.
    */

    public String getName() {
        return this.name;
    }

    /**
    * Retrieves a {@link Chunk} at the coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    public Chunk getChunkAt(int x, int y, int z) {
        return this.provider.provide(x, y, z);
    }

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

    public void tick() {
        this.provider.tick();
    }

    /**
    * Saves this {@link Level}.
    */

    public void save() {
        System.out.println("[Level:" + this.getName() + "]: Saved!");
    }
}