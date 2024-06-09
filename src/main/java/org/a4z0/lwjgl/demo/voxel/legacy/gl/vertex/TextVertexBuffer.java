package org.a4z0.lwjgl.demo.voxel.legacy.gl.vertex;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

/**
* Represents a Vertex Buffer.
*/

public class TextVertexBuffer implements IVertexBuffer {

    protected final VertexArrayObject VAO;
    protected final VertexBufferObject VBO;

    public static int ELEMENTS_SIZE = 3 + 2 + 3 + 1;
    public static int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;

    protected final int i;

    /**
    * Construct a {@link TextVertexBuffer}.
    *
    * @param x ...
    * @param i ...
    */

    public TextVertexBuffer(float[] x, int i) {
        this.i = i;

        (this.VBO = new VertexBufferObject()).bind();
        (this.VAO = new VertexArrayObject()).bind();

        this.VBO.buffer(x, GL_STATIC_DRAW);

        //glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, true, ELEMENTS_STRIDE * Float.BYTES, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, true, ELEMENTS_STRIDE * Float.BYTES, (3) * Float.BYTES);
        glVertexAttribPointer(2, 3, GL_FLOAT, true, ELEMENTS_STRIDE * Float.BYTES, (3 + 2) * Float.BYTES);
        glVertexAttribPointer(3, 1, GL_FLOAT, true, ELEMENTS_STRIDE * Float.BYTES, (3 + 2 + 3) * Float.BYTES);

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