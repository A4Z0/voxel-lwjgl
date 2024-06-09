package org.a4z0.lwjgl.demo.voxel.legacy.done.chunk;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.chunk.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.level.Level;

import java.util.HashMap;
import java.util.Map;

/**
* ...
*/

public class ChunkProvider {

    protected final Level Level;
    protected final Map<ChunkPosition, Chunk> CHUNK_MAP = new HashMap<>();

    /**
    * Construct a {@link ChunkProvider}.
    */

    public ChunkProvider(Level Level) {
        this.Level = Level;
    }

    /**
    * ...
    *
    * @return ...
    */

    public Level getLevel() {
        return this.Level;
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public Chunk get(int x, int y, int z) {
        return this.get(new ChunkPosition(x, y, z));
    }

    /**
    * ...
    *
    * @param p ...
    *
    * @return ...
    */

    public synchronized Chunk get(ChunkPosition p) {
        for(Map.Entry<ChunkPosition, Chunk> Entry : this.CHUNK_MAP.entrySet())
            if(Entry.getKey().equals(p))
                return Entry.getValue();

        return new Chunk(this, p.getX(), p.getY(), p.getZ());
    }

    /**
    * ...
    *
    * @param Chunk ...
    */

    public synchronized void put(Chunk Chunk) {
        this.CHUNK_MAP.put(new ChunkPosition(Chunk.getX(), Chunk.getY(), Chunk.getZ()), Chunk);
    }

    public synchronized boolean exists(ChunkPosition p) {
        return this.get(p) != null;
    }
}