package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.gl.voxel.Voxel;

/**
* Represents an Empty Chunk.
* Contains only its coordinates and the {@link LevelAccess} where it resides.
*
* <br>
*
* It should only be used when outside the {@link LevelAccess}'s boundaries.
*/

public class EmptyChunk implements Chunk {

    protected final LevelAccess level;
    protected final ChunkPosition position;

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param level {@link LevelAccess} that this {@link EmptyChunk} is.
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public EmptyChunk(LevelAccess level, int x, int z) {
        this(level, new ChunkPosition(x, z));
    }

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param level {@link LevelAccess} that this {@link EmptyChunk} is.
    * @param position {@link ChunkPosition Position} that this {@link EmptyChunk} is.
    */

    public EmptyChunk(LevelAccess level, ChunkPosition position) {
        this.level = level;
        this.position = position;
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

    }

    @Override
    public Voxel getVoxel(int x, int y, int z) {
        return Voxel.EMPTY_VOXEL;
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