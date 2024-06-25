package org.a4z0.lwjgl.demo.render.renderer.outline;

import jdk.jfr.Experimental;
import org.a4z0.lwjgl.demo.shader.Shaders;
import org.a4z0.lwjgl.demo.util.ByteBuf;
import org.a4z0.lwjgl.demo.math.AABBfc;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

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
        ByteBuf buffer = new ByteBuf()
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

        int glVAO = glGenVertexArrays();
        int glVBO = glGenBuffers();

        glBindVertexArray(glVAO);
        glBindBuffer(GL_ARRAY_BUFFER, glVBO);

        glBufferData(glVBO, buffer.asByteBuffer(), GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, true, 0, 0);

        Shaders.OUTLINE_SHADER_PROGRAM.bind();
        Shaders.OUTLINE_SHADER_PROGRAM.setUniform4f("outline_color", r, g, b, a);
        Shaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("transformation", new Matrix4f().translate(x, y, z));

        glLineWidth(width);
        glDrawArrays(GL_LINES, 0, 24);
        glDisableVertexAttribArray(0);

        glLineWidth(1f);

        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        Shaders.OUTLINE_SHADER_PROGRAM.unbind();
    }
}