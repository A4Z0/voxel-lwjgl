package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;

/**
* ...
*/

public interface IChunkProvider {

    /**
    * @return ...
    */

    LevelAccess getLevel();

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    Chunk getChunkOrNull(int x, int z);

    /**
    * ...
    *
    * @param chunk ...
    */

    default void dump(Chunk chunk) {
        this.dump(chunk.getX(), chunk.getZ());
    }

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    */

    void dump(int x, int z);

    /**
    * ...
    */

    void tick();
}