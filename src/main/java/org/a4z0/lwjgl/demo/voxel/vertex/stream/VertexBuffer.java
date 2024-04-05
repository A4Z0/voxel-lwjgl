package org.a4z0.lwjgl.demo.voxel.vertex.stream;

import org.a4z0.lwjgl.demo.voxel.vertex.VertexArrayObject;
import org.a4z0.lwjgl.demo.voxel.vertex.VertexBufferObject;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;

/**
* Represents a Vertex Buffer.
*/

public class VertexBuffer implements Buffer<float[]> {

    public static final int ELEMENTS_SIZE = 3;

    protected final VertexArrayObject VAO;
    protected final VertexBufferObject VBO;

    /**
    * Construct a {@link VertexBuffer}.
    */

    public VertexBuffer() {
        this.VAO = new VertexArrayObject();
        this.VBO = new VertexBufferObject();

        this.VAO.bind();
        this.VBO.bind();
        this.VAO.attribute(0, 3, GL_FLOAT, true, ELEMENTS_SIZE * Float.BYTES, 0);
        this.VBO.unbind();
        this.VAO.unbind();
    }

    @Override
    public VertexArrayObject getVAO() {
        return this.VAO;
    }

    @Override
    public VertexBufferObject getVBO() {
        return this.VBO;
    }

    @Override
    public void consume(float[] e) {
        this.VBO.bind();
        this.VBO.buffer(e, GL_DYNAMIC_DRAW);
        this.VBO.unbind();
    }

    @Override
    public void delete() {
        this.VAO.delete();
        this.VBO.delete();
    }
}