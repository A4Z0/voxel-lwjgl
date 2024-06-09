package org.a4z0.lwjgl.demo.voxel.render.renderer.outline;

import jdk.jfr.Experimental;
import org.a4z0.lwjgl.demo.voxel.math.AABBfc;
import org.a4z0.lwjgl.demo.voxel.render.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.legacy.done.buffer.DynamicBuffer;
import org.a4z0.lwjgl.demo.voxel.legacy.gl.vertex.VertexBuffer;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

@Experimental
public class OutlineRenderer {
    
    /**
    * Construct a {@link OutlineRenderer}.
    */

    public OutlineRenderer() {

    }

    // TODO: Describe what each parameter does.
    public void render(AABBfc AABB, float x, float y, float z, float r, float g, float b, float a, float width) {
        DynamicBuffer Stream = new DynamicBuffer()
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getUpperZ())
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getUpperZ())
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getLowerY()).put(AABB.getUpperZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getLowerX()).put(AABB.getUpperY()).put(AABB.getUpperZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getLowerZ())
            .put(AABB.getUpperX()).put(AABB.getUpperY()).put(AABB.getUpperZ());
                
        VertexBuffer Buffer = new VertexBuffer(Stream.array(), 0);

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

        Buffer.delete();
    }
}