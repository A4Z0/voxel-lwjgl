package org.a4z0.lwjgl.demo.voxel.level.server;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.legacy.done.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.world.server.WorldServer;

import java.util.UUID;

public class LevelServer extends Level {

    protected final ChunkProvider Provider = new ChunkProvider(this);

    /**
    * Construct a {@link LevelServer}.
    *
    * @param world {@link WorldServer} this {@link LevelServer} is in.
    * @param uuid {@link LevelServer}'s {@link UUID}.
    * @param name {@link LevelServer}'s Name.
    * @param seed {@link LevelServer}'s Seed.
    */

    public LevelServer(WorldServer world, UUID uuid, String name, long seed) {
        super(world, uuid, name, seed);
    }

    @Override
    public Chunk getChunkAt(int x, int y, int z) {
        return this.Provider.get(x, y, z);
    }

    @Override
    public void tick() {

    }

    @Override
    public void save() {
        System.out.println("[Level -> Server:" + this.getName() + "]: Saved!");
    }
}