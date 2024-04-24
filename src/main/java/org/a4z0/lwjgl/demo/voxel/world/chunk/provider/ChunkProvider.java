package org.a4z0.lwjgl.demo.voxel.world.chunk.provider;

import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.world.chunk.IChunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;

import java.util.Arrays;

/**
* Represents a Chunk Provider.
*/

public class ChunkProvider implements IChunkProvider {

    protected final World WORLD;

    protected IChunk[] LOADED_CHUNKS = new IChunk[4];

    /**
    * Construct a {@link ChunkProvider}.
    */

    public ChunkProvider(World WORLD) {
        this.WORLD = WORLD;
    }

    @Override
    public IChunk getChunkAt(int x, int z) {
        for(IChunk CHUNK : LOADED_CHUNKS)
            if(CHUNK != null && ChunkPosition.asBlock(CHUNK.getX(), CHUNK.getZ()) == ChunkPosition.asBlock(x, z))
                return CHUNK;

        synchronized (this) {
            for(int i = 0; i < LOADED_CHUNKS.length; i++)
                if(LOADED_CHUNKS[i] == null)
                    return (LOADED_CHUNKS[i] = new Chunk(this.WORLD, x, z, null));

            LOADED_CHUNKS = Arrays.copyOf(LOADED_CHUNKS, LOADED_CHUNKS.length * 2);

            return (LOADED_CHUNKS[LOADED_CHUNKS.length / 2] = new Chunk(this.WORLD, x, z, null));
        }
    }

    public IChunk[] getChunks() {
        return this.LOADED_CHUNKS;
    }
}