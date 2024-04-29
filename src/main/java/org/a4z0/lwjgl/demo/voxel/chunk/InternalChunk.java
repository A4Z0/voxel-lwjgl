package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.palette.Palette;
import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.gl.voxel.Voxel;

/**
* Represents a Chunk.
*/

public class InternalChunk implements Chunk {

    protected final LevelAccess level;
    protected final ChunkPosition position;
    protected final Palette<Voxel> palette;

    /**
    * Construct a {@link InternalChunk}.
    *
    * @param level ...
    * @param x ...
    * @param z ...
    * @param palette ...
    */

    public InternalChunk(LevelAccess level, int x, int z, Palette<Voxel> palette) {
        this(level, new ChunkPosition(x, z), palette);
    }

    /**
    * Construct a {@link InternalChunk}.
    *
    * @param level ...
    * @param position ...
    * @param palette ...
    */

    public InternalChunk(LevelAccess level, ChunkPosition position, Palette<Voxel> palette) {
        this.level = level;
        this.position = position;
        this.palette = new Palette<>(CHUNK_SIZE_X, CHUNK_SIZE_Y, CHUNK_SIZE_Z);
        this.palette.fill(Voxel.EMPTY_VOXEL);
    }

    @Override
    public LevelAccess getLevel() {
        return this.level;
    }

    @Override
    public int getX() {
        return this.position.getX();
    }

    @Override
    public int getZ() {
        return this.position.getZ();
    }

    @Override
    public void setVoxel(int x, int y, int z, Voxel voxel) {
        this.palette.set(x, y, z, voxel);
    }

    @Override
    public Voxel getVoxel(int x, int y, int z) {
        return this.palette.get(x, y, z);
    }

    @Override
    public boolean load(boolean g) {
        return true;
    }

    @Override
    public boolean unload(boolean l) {
        return true;
    }
}