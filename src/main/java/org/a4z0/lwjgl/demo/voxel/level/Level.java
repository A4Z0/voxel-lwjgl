package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.chunk.IChunkProvider;
import org.a4z0.lwjgl.demo.voxel.chunk.InternalChunkProvider;

/**
* Represents a Level.
*/

public class Level implements LevelAccess {

    protected final long seed;
    protected final IChunkProvider provider;

    /**
    * Construct a {@link Level}.
    *
    * @param seed ...
    */

    public Level(long seed) {
        this.seed = seed;
        this.provider = new InternalChunkProvider(this);
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        return this.provider.getChunkOrNull(x, z);
    }
}