package org.a4z0.lwjgl.demo.voxel.world.pipeline;

import org.a4z0.lwjgl.demo.voxel.collision.AABB3i;
import org.a4z0.lwjgl.demo.voxel.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.world.chunk.provider.IChunkProvider;
import org.a4z0.lwjgl.demo.voxel.world.mesh.GenericMesh;
import org.a4z0.lwjgl.demo.voxel.world.mesh.Mesh;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class Pipeline implements IPipeline {

    public static final Matrix4f MATRIX_4_F = new Matrix4f().translate(0, 0, 0);

    // Flags
    public static final byte QUEUED = 0x01;
    public static final byte BUFFERED = 0x02;

    // Pipeline
    protected final IChunkProvider CHUNK_PROVIDER;
    protected final Mesh MESH;
    protected byte STATE = 0;

    // Object
    protected final int glVAO;
    protected final int glVBO;

    // Buffer
    protected float[] glBufferData;
    protected int glCount;

    /**
    * Construct a {@link Pipeline}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public Pipeline(IChunkProvider Provider, int x, int y, int z) {
        this(Provider, x, y, z, x + LAYER_SIZE_X_BIT, y + LAYER_SIZE_Y, z + LAYER_SIZE_Z);
    }

    /**
    * Construct a {@link Pipeline}.
    *
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    */

    public Pipeline(IChunkProvider Provider, int x1, int y1, int z1, int x2, int y2, int z2) {
        this(Provider, new AABB3i(x1, y1, z1, x2, y2, z2));
    }

    /**
    * Construct a {@link Pipeline}.
    *
    * @param AABB ...
    */

    public Pipeline(IChunkProvider Provider, AABB3i AABB) {
        this.glVAO = glGenVertexArrays();
        this.glVBO = glGenBuffers();

        this.CHUNK_PROVIDER = Provider;
        this.MESH = new GenericMesh(AABB);
    }

    @Override
    public boolean isReadyForRendering() {
        return (this.STATE & 0x03) != 0;
    }

    @Override
    public void update() {
        if((this.STATE & 0x01) == 0) {
            this.STATE |= 0x01;

            CompletableFuture.runAsync(() -> this.glBufferData = this.MESH.build(this.CHUNK_PROVIDER).array());
        } else if (this.glBufferData != null) {
            glBindVertexArray(this.glVAO);
            glBindBuffer(GL_ARRAY_BUFFER, this.glVBO);

            glBufferData(GL_ARRAY_BUFFER, this.glBufferData, GL_DYNAMIC_DRAW);

            this.glCount = (this.glBufferData.length / ELEMENTS_SIZE);
            this.glBufferData = null;

            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, true, ELEMENTS_STRIDE, 0);

            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 4, GL_FLOAT, true, ELEMENTS_STRIDE, (3) * Float.BYTES);

            glEnableVertexAttribArray(2);
            glVertexAttribPointer(2, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (3 + 4) * Float.BYTES);

            glBindVertexArray(0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            this.STATE |= 0x02;
        };
    }

    @Override
    public void render() {
        if(!this.isReadyForRendering())
            return;

        glBindVertexArray(this.glVAO);

        VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("transformation", MATRIX_4_F);

        glDrawArrays(GL_TRIANGLES, 0, this.glCount);
        glBindVertexArray(0);
    }

    @Override
    public void delete() {
        glDeleteVertexArrays(this.glVAO);
        glDeleteBuffers(this.glVBO);
    }
}