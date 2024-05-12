package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.level.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.voxel.level.chunk.InternalChunk;
import org.a4z0.lwjgl.demo.voxel.block.BlockState;

/**
* Represents an Internal Level.
*/

public class InternalLevel implements Level {

    protected final long s;
    protected final ChunkProvider p;

    /**
    * Construct a {@link InternalLevel}.
    *
    * @param s Seed to be used in {@link Level}'s Generation.
    * @param p {@link ChunkProvider Provider} that will supply the {@link Chunk Chunks}.
    */

    public InternalLevel(long s, ChunkProvider p) {
        this.s = s;
        this.p = p;
    }

    @Override
    public long getSeed() {
        return this.s;
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        return this.p.exists(x, z) ? this.p.getChunkAt(x, z) : this.p.setChunkAt(x, z, new InternalChunk(this.p, this, x, z));
    }

    @Override
    public BlockState getBlockAt(int x, int y, int z) {
        return this.getChunkAt(x, z).getBlockAt(x, y, z);
    }
}