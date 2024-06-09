package org.a4z0.lwjgl.demo.voxel.layer;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.layer.mesh.Mesh;
import org.a4z0.lwjgl.demo.voxel.legacy.done.buffer.DynamicBuffer;
import org.a4z0.lwjgl.demo.voxel.render.shader.pre.VGShaders;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class ChunkRender {

    public static final byte COMPUTED = 0x01;
    public static final byte BUFFERED = 0x02;

    public static final Matrix4f MATRIX_4_F = new Matrix4f().translate(0, 0, 0);

    public static int ELEMENTS_SIZE = 3 + 4 + 1;
    public static int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;

    protected int VAO;
    protected int VBO;

    protected Chunk CHUNK;

    protected int MIN_X, MAX_X;
    protected int MIN_Y, MAX_Y;
    protected int MIN_Z, MAX_Z;

    protected DynamicBuffer BUFFER = new DynamicBuffer();
    protected CompletableFuture<Void> STREAM;

    protected float[] DATA;
    protected int COUNT;

    protected byte STATE;

    /**
    * Construct a {@link ChunkRender}.
    *
    * @param CHUNK ...
    */

    public ChunkRender(Chunk CHUNK, int MIN_X, int MIN_Y, int MIN_Z, int MAX_X, int MAX_Y, int MAX_Z) {
        this.CHUNK = CHUNK;

        this.MIN_X = MIN_X;
        this.MIN_Y = MIN_Y;
        this.MIN_Z = MIN_Z;
        this.MAX_X = MAX_X;
        this.MAX_Y = MAX_Y;
        this.MAX_Z = MAX_Z;
    }

    /**
    * Updates this {@link ChunkRender}.
    */

    public void update() {
        if(!this._h0x01()) {

            // Uploads the Vertex Data into the Buffer asynchronously.

            this.STREAM = CompletableFuture.runAsync(() -> {
                Mesh.build(this.BUFFER, this.CHUNK, this.MIN_X, this.MIN_Y, this.MIN_Z, this.MAX_X, this.MAX_Y, this.MAX_Z);

                this.COUNT = (this.DATA = this.BUFFER.array()).length / ELEMENTS_SIZE;
            });

            this.STATE |= COMPUTED;

        } else if(this._h0x01()) {

            // Sets the Vertex Data and its Attributes in the Vertex Buffer Object.

            if(this.STREAM == null || !this.STREAM.isDone())
                return;

            this.BUFFER.flush();
            this.STREAM = null;

            this.VAO = glGenVertexArrays();
            this.VBO = glGenBuffers();

            glBindVertexArray(this.VAO);
            glBindBuffer(GL_ARRAY_BUFFER, this.VBO);
            glBufferData(GL_ARRAY_BUFFER, this.DATA, GL_DYNAMIC_DRAW);

            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, true, ELEMENTS_STRIDE, 0);

            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 4, GL_FLOAT, true, ELEMENTS_STRIDE, (3) * Float.BYTES);

            glEnableVertexAttribArray(2);
            glVertexAttribPointer(2, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (3 + 4) * Float.BYTES);

            glBindVertexArray(0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            this.DATA = null;
            this.STATE |= BUFFERED;
        }
    }

    /**
    * Renders this {@link ChunkRender}.
    */

    public void render() {
        if(!this._e0x03())
            return;

        glBindVertexArray(this.VAO);

        VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("transformation", MATRIX_4_F);

        glDrawArrays(GL_TRIANGLES, 0, this.COUNT);
        glBindVertexArray(0);
    }

    /**
    * Deletes this {@link ChunkRender}.
    */

    public void delete() {
        this.delete(false);
    }

    /**
    * Deletes this {@link ChunkRender}.
    *
    * @param b Delete just the data?
    */

    public void delete(boolean b) {
        glDeleteVertexArrays(this.VAO);
        glDeleteBuffers(this.VBO);

        if(!b) {
            this.VAO = 0;
            this.VBO = 0;
            this.COUNT = 0;
            this.DATA = null;
            this.STREAM = null;
        }

        this.STATE = 0;
    }

    /**
    * @return ...
    */

    public boolean _h0x01() {
        return (this.STATE & COMPUTED) != 0;
    }

    /**
    * @return true if this {@link ChunkRender} is ready for rendering, false otherwise.
    */

    public boolean _e0x03() {
        return this.STATE == (COMPUTED | BUFFERED);
    }
}