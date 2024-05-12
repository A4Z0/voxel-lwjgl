package org.a4z0.lwjgl.demo.voxel.level.chunk;

/**
* Represents a Chunk Provider.
*/

public interface ChunkProvider {

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    Chunk getChunkAt(int x, int z);

    /**
    * ...
    *
    * @param i ...
    *
    * @return ...
    */

    Chunk getChunkAt(long i);

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    * @param chunk ...
    */

    Chunk setChunkAt(int x, int z, Chunk chunk);

    /**
    * ...
    *
    * @param i ...
    * @param chunk ...
    */

    Chunk setChunkAt(long i, Chunk chunk);

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    */

    Chunk delChunkAt(int x, int z);

    /**
    * ...
    *
    * @param i ...
    */

    Chunk delChunkAt(long i);

    boolean exists(int x, int z);

    boolean exists(long i);
}