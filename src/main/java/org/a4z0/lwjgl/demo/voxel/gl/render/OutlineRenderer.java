package org.a4z0.lwjgl.demo.voxel.gl.render;

import jdk.jfr.Experimental;
import org.a4z0.lwjgl.demo.voxel.legacy.collision.AABB;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexBuffer;
import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.pipeline.IPipeline;

/**
* ...
*/

@Experimental
public class OutlineRenderer {

    /** Represents the bounding edges of a {@link IPipeline}. */
    public static final AABB VOXEL_BOUNDARIES = new AABB(0, 0, 0, -1, -1, -1);

    /** Represents the bounding edges of a {@link Chunk}. */
    public static final AABB BOUNDARIES = new AABB(Chunk.CHUNK_SIZE_X -1, Chunk.CHUNK_SIZE_Y -1, Chunk.CHUNK_SIZE_Z -1, -1, -1, -1);

    /** Represents the bounding edges of a {@link IPipeline} */
    public static final AABB BLOCK_BOUNDARIES = new AABB(IPipeline.LAYER_SIZE_X -1, IPipeline.LAYER_SIZE_Y -1, IPipeline.LAYER_SIZE_Z -1, -1, -1, -1);

    /**
    * Construct a {@link OutlineRenderer}.
    */

    public OutlineRenderer() {

    }

    // TODO: Describe what each parameter does.
    public void render(Chunk chunk, float r, float g, float b, float a, float w) {
        this.render(BOUNDARIES, chunk.getX(), 0, chunk.getZ(), r, g, b, a, w);

        /*for(Layer layer : chunk.getLayerProvider().getLayers()) {
            if(layer == null)
                continue;

            this.render(BLOCK_BOUNDARIES, layer.getX(), layer.getY(), layer.getZ(), r, g, b, a, w);
        }*/
    }

    // TODO: Describe what each parameter does.
    public void render(AABB aabb, float x, float y, float z, float r, float g, float b, float a, float w) {
        this.render(aabb.getLowerX(), aabb.getLowerY(), aabb.getLowerZ(), aabb.getUpperX(), aabb.getUpperY(), aabb.getUpperZ(), x, y, z, r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, float r, float g, float b, float a, float width) {
        this.render(null/*new VertexBuffer()*/, x1, y1, z1, x2, y2, z2, x, y, z, r, g, b, a, width);
    }

    // TODO: Describe what each parameter does.
    public void render(VertexBuffer Buffer, float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, float r, float g, float b, float a, float width) {
        /*new VertexStream()
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
        .consume(Buffer);

        VGShaders.OUTLINE_SHADER_PROGRAM.bind();

        glBindVertexArray(Buffer.getVAO().getID());
        glEnableVertexAttribArray(0);

        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4f("outline_color", r, g, b, a);
        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("transformation", new Matrix4f().translate(x, y, z));

        glLineWidth(width);
        glDrawArrays(GL_LINES, 0, 24);
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        Buffer.getVAO().unbind();
        VGShaders.OUTLINE_SHADER_PROGRAM.unbind();

        Buffer.delete();*/
    }
}