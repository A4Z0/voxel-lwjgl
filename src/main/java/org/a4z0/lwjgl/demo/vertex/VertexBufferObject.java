package org.a4z0.lwjgl.demo.vertex;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
* Represents a Vertex Buffer Object.
*/

public class VertexBufferObject {

    protected final int glVBO;

    /**
    * Construct a {@link VertexBufferObject}.
    */

    public VertexBufferObject() {
        this.glVBO = glGenBuffers();
    }

    /**
    * @return this {@link VertexBufferObject VBO} ID.
    */

    public int getID() {
        return this.glVBO;
    }

    /**
    * Binds this {@link VertexBufferObject VBO} to the current context.
    */

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.glVBO);
    }

    /**
    * Unbinds this {@link VertexBufferObject VBO} from the current context.
    */

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
    * Deletes this {@link VertexBufferObject VBO}.
    */

    public void delete() {
        glDeleteBuffers(this.glVBO);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(ByteBuffer glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBufferData, glUsage);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(byte[] glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, ByteBuffer.wrap(glBufferData), glUsage);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(ShortBuffer glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBufferData, glUsage);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(short[] glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBufferData, glUsage);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(FloatBuffer glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBufferData, glUsage);
    }

    /**
    * Uploads data into the {@link VertexBufferObject VBO} in the current context.
    *
    * @param glBufferData Data to be uploaded.
    * @param glUsage Buffer usage.
    */

    public void buffer(float[] glBufferData, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBufferData, glUsage);
    }
}