package org.a4z0.lwjgl.demo.voxel.world.mesh;

import org.a4z0.lwjgl.demo.voxel.vertex.VertexStream;
import org.a4z0.lwjgl.demo.voxel.world.chunk.provider.IChunkProvider;

/**
* Represents a Mesh.
*/

public interface Mesh {

    /**
    * Builds a {@link Mesh} based on a {@link IChunkProvider}.
    *
    * @param provider {@link IChunkProvider} on which this Mesh will be built.
    *
    * @return a {@link VertexStream}.
    */

    default VertexStream build(IChunkProvider provider) {
        return this.build(provider, new VertexStream());
    }

    /**
    * Builds a {@link Mesh} based on a {@link IChunkProvider}.
    *
    * @param provider {@link IChunkProvider} on which this Mesh will be built.
    * @param stream {@link VertexStream} that will have the {@link Mesh} data.
    *
    * @return a {@link VertexStream}.
    */

    VertexStream build(IChunkProvider provider, VertexStream stream);
}