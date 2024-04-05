package org.a4z0.lwjgl.demo.voxel.world.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.vertex.VertexArrayObject;
import org.a4z0.lwjgl.demo.voxel.vertex.VertexBufferObject;
import org.a4z0.lwjgl.demo.voxel.vertex.stream.VertexStream;
import org.a4z0.lwjgl.demo.voxel.voxel.Mesh;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* Represents a {@link Chunk} {@link Layer}.
*/

public class Layer {

    public static final int LAYER_SIZE_X = 16;
    public static final int LAYER_SIZE_Y = 16;
    public static final int LAYER_SIZE_Z = 16;

    public static final int LAYER_SIZE_X_SQRT = (int) Math.sqrt(LAYER_SIZE_X);
    public static final int LAYER_SIZE_Y_SQRT = (int) Math.sqrt(LAYER_SIZE_Y);
    public static final int LAYER_SIZE_Z_SQRT = (int) Math.sqrt(LAYER_SIZE_Z);

    private static final int ELEMENTS_SIZE = 3 + 4 + 1;

    protected VertexArrayObject VAO;
    protected VertexBufferObject VBO;

    protected Future<VertexStream> stream;
    protected float[] values;
    protected int vertices;

    protected final Chunk chunk;

    protected final int x;
    protected final int y;
    protected final int z;

    /**
    * Construct a {@link Chunk}.
    *
    * @param chunk {@link Chunk} that this {@link Layer} is.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Layer(Chunk chunk, int x, int y, int z) {
        this.chunk = chunk;
        this.x = (x >> LAYER_SIZE_X_SQRT) * LAYER_SIZE_X;
        this.y = (y >> LAYER_SIZE_Y_SQRT) * LAYER_SIZE_Y;
        this.z = (z >> LAYER_SIZE_Z_SQRT) * LAYER_SIZE_Z;
    }

    /**
    * @return the X-Axis where this {@link Layer} is.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis where this {@link Layer} is.
    */

    public int getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis where this {@link Layer} is.
    */

    public int getZ() {
        return this.z;
    }

    /**
    * @return true if ready to render, false otherwise.
    */

    public boolean isReadyForRendering() {
        return this.vertices != 0 && this.values != null;
    }

    /**
    * Updates this {@link Layer}.
    */

    public void update(long l) {
        if(this.values == null && this.stream == null) {
            this.stream = CompletableFuture.supplyAsync(() -> Mesh.create(this.x, this.y, this.z, this.x + LAYER_SIZE_X -1, this.y + LAYER_SIZE_Y -1, this.z + LAYER_SIZE_Z -1));

            return;
        }

        if(((System.nanoTime() - l) / 1E9d) >= (1.0 / 90.0))
            return;

        if(this.stream == null || !this.stream.isDone())
            return;

        if(this.VAO != null)
            this.VAO.delete();

        if(this.VBO != null)
            this.VBO.delete();

        VertexStream Stream;

        try {
            Stream = this.stream.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        this.values = Stream.values();
        this.vertices = (Stream.size() / ELEMENTS_SIZE);

        this.VBO = new VertexBufferObject();
        this.VBO.bind();

        this.VBO.buffer(this.values, GL_STATIC_DRAW);

        this.VAO = new VertexArrayObject();
        this.VAO.bind();

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        this.VAO.attribute(0, 3, GL_FLOAT, true, ELEMENTS_SIZE * Float.BYTES, 0);
        this.VAO.attribute(1, 4, GL_FLOAT, true, ELEMENTS_SIZE * Float.BYTES, (3) * Float.BYTES);
        this.VAO.attribute(2, 1, GL_FLOAT, true, ELEMENTS_SIZE * Float.BYTES, (3 + 4) * Float.BYTES);

        this.VAO.unbind();
        this.VBO.unbind();

        this.stream = null;
    }

    /**
    * Renders this {@link Layer}.
    */

    public void render() {
        if(!this.isReadyForRendering())
            return;

        VGShaders.VOXEL_SHADER_PROGRAM.bind();
        glBindVertexArray(this.VAO.getID());
        VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("transformation", new Matrix4f().translate(0, 0, 0));
        glDrawArrays(GL_TRIANGLES, 0, this.vertices);
        glBindVertexArray(0);

        VGShaders.VOXEL_SHADER_PROGRAM.unbind();
    }

    /**
    * Deletes this {@link Layer}.
    *
    * @param lazy True for lazy, false otherwise.
    */

    public void delete(boolean lazy) {
        if(lazy) {
            this.stream = CompletableFuture.supplyAsync(() -> Mesh.create(this.x, this.y, this.z, this.x + LAYER_SIZE_X -1, this.y + LAYER_SIZE_Y -1, this.z + LAYER_SIZE_Z -1));

            return;
        }

        if (this.VAO != null)
            this.VAO.delete();

        if (this.VBO != null)
            this.VBO.delete();

        this.VAO = null;
        this.VBO = null;
        this.values = null;
        this.vertices = 0;
        this.stream = null;
    }
}