package org.a4z0.lwjgl.demo.voxel.legacy.gl.vertex;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

/**
* Represents a Vertex Buffer.
*/

public class FontBuffer implements IVertexBuffer {

    protected final VertexArrayObject VAO;
    protected final VertexBufferObject VBO;

    protected final int i;

    /**
    * Construct a {@link FontBuffer}.
    *
    * @param x ...
    * @param i ...
    */

    public FontBuffer(float[] x, int i) {
        this.i = i;

        (this.VBO = new VertexBufferObject()).bind();
        (this.VAO = new VertexArrayObject()).bind();

        this.VBO.buffer(x, GL_STATIC_DRAW);
        this.VAO.attribute(0, 3, GL_FLOAT, true, (3 + 2), 0);
        this.VAO.attribute(1, 2, GL_FLOAT, true, (3 + 2), (3) * Float.BYTES);

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