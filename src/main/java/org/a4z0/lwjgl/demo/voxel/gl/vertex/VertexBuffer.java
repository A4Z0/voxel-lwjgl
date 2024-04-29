package org.a4z0.lwjgl.demo.voxel.gl.vertex;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

/**
* Represents a Vertex Buffer.
*/

public class VertexBuffer implements IVertexBuffer {

    protected final VertexArrayObject VAO;
    protected final VertexBufferObject VBO;

    protected final int i;

    /**
    * Construct a {@link VertexBuffer}.
    *
    * @param x ...
    * @param i ...
    */

    public VertexBuffer(float[] x, int i) {
        this.i = i;

        (this.VBO = new VertexBufferObject()).bind();
        (this.VAO = new VertexArrayObject()).bind();

        this.VBO.buffer(x, GL_STATIC_DRAW);
        this.VAO.attribute(0, 3, GL_FLOAT, true, this.i * Float.BYTES, 0);

        this.VBO.unbind();
        this.VAO.unbind();
    }

    @Override
    public VertexArrayObject getVAO() {
        return this.VAO;
    }

    public void upload() {

    }

    @Override
    public void delete() {
        this.VAO.delete();
        this.VBO.delete();
    }
}