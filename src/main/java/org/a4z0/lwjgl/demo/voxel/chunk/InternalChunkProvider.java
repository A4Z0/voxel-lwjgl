package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;

/**
* ...
*/

public class InternalChunkProvider implements IChunkProvider {

    protected final LevelAccess Level;
    protected final Chunk[] Chunks = new Chunk[4];

    /**
    * Construct a {@link InternalChunkProvider}.
    *
    * @param level ...
    */

    public InternalChunkProvider(LevelAccess level) {
        this.Level = level;
    }

    @Override
    public LevelAccess getLevel() {
        return this.Level;
    }

    @Override
    public Chunk getChunkOrNull(int x, int z) {
        for(Chunk Chunk : this.Chunks)
            if(Chunk != null && ChunkPosition.asBlock(Chunk.getX(), Chunk.getZ()) == ChunkPosition.asBlock(x, z))
                return Chunk;

        return null;
    }

    @Override
    public void dump(int x, int z) {
        for(int i = 0; i < this.Chunks.length; i++) {
            Chunk Chunk = this.Chunks[i];

            if(Chunk != null && ChunkPosition.asBlock(Chunk.getX(), Chunk.getZ()) == ChunkPosition.asBlock(x, z))
                this.Chunks[i] = null;
        }
    }

    @Override
    public void tick() {

    }
}