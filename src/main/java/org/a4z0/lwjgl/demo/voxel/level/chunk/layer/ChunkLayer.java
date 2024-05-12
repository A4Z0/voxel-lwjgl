package org.a4z0.lwjgl.demo.voxel.level.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.gl.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexStream;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class ChunkLayer {

    public static final byte COMPUTED = 0x01;
    public static final byte BUFFERED = 0x02;

    public static final Matrix4f MATRIX_4_F = new Matrix4f().translate(0, 0, 0);

    public static int ELEMENTS_SIZE = 3 + 4 + 1;
    public static int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;

    protected int VAO;
    protected int VBO;

    protected Chunk CHUNK;
    protected int Y;

    protected CompletableFuture<VertexStream> STREAM;

    protected float[] DATA;
    protected int COUNT;

    protected byte STATE;

    /**
    * Construct a {@link ChunkLayer}.
    *
    * @param CHUNK {@link Chunk} to be attached.
    * @param Y Y-axis.
    */

    public ChunkLayer(Chunk CHUNK, int Y) {
        this.CHUNK = CHUNK;
        this.Y = Y;
    }

    public Chunk getChunk() {
        return this.CHUNK;
    }

    /**
    * Sets the {@link Chunk} of this {@link ChunkLayer}.
    *
    * @param CHUNK {@link Chunk} to be attached.
    */

    public void setChunk(Chunk CHUNK) {
        if(this.STATE == (byte) 0xFF)
            return;

        this.CHUNK = CHUNK;
    }


    public int getY() {
        return this.Y;
    }

    /**
    * Sets the Y-Axis of this {@link ChunkLayer}.
    *
    * @param Y Y-Axis.
    */

    public void setY(int Y) {
        if(this.STATE == (byte) 0xFF)
            return;

        this.Y = Y;
    }

    /**
    * Updates this {@link ChunkLayer}.
    */

    public void update() {
        if(!this._h0x01()) {

            // Uploads the Vertex Data into the Buffer asynchronously.

            this.STREAM = CompletableFuture.supplyAsync(() -> ChunkLayerMesh.create(this));
            this.STATE |= COMPUTED;

        } else if(this._h0x01()) {

            // Sets the Vertex Data and its Attributes in the Vertex Buffer Object.

            if(this.STREAM == null || !this.STREAM.isDone())
                return;

            try {
                this.COUNT = (this.DATA = this.STREAM.get().array()).length;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Unable to obtain the Stream data.");
            }

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
    * Renders this {@link ChunkLayer}.
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
    * Deletes this {@link ChunkLayer}.
    */

    public void delete() {
        this.delete(false);
    }

    /**
    * Deletes this {@link ChunkLayer}.
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
    * @return true if this {@link ChunkLayer} is ready for rendering, false otherwise.
    */

    public boolean _e0x03() {
        return this.STATE == (COMPUTED | BUFFERED);
    }
}