package org.a4z0.lwjgl.demo.voxel.render;

@FunctionalInterface
public interface RenderExecutor {

    /**
    * Executes a Render.
    *
    * @param glShader Shader being used.
    */

    void execute(int glShader);
}