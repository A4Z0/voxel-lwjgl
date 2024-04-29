package org.a4z0.lwjgl.demo.voxel.gl.vertex;

/**
* Represents a Vertex Buffer.
*/

public interface IVertexBuffer {

    /**
    * @return the {@link VertexArrayObject VAO} in this {@link IVertexBuffer}.
    */

    VertexArrayObject getVAO();

    /**
    * Deletes this {@link IVertexBuffer}.
    */

    void delete();
}