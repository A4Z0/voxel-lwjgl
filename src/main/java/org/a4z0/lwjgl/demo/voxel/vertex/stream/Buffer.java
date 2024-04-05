package org.a4z0.lwjgl.demo.voxel.vertex.stream;

import org.a4z0.lwjgl.demo.voxel.vertex.VertexBufferObject;
import org.a4z0.lwjgl.demo.voxel.vertex.VertexArrayObject;

/**
* Represents a {@link Buffer}.
*/

public interface Buffer<T> extends Stream.Consumer<T> {

    /**
    * @return the {@link VertexArrayObject VAO} in this {@link Buffer}.
    */

    VertexArrayObject getVAO();

    /**
    * @return the {@link VertexBufferObject VBO} in this {@link Buffer}.
    */

    VertexBufferObject getVBO();

    /**
    * Deletes this {@link Buffer}.
    */

    void delete();
}