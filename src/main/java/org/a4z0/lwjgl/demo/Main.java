package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.chunk.Chunk;
import org.a4z0.lwjgl.demo.layer.ChunkLayer;
import org.a4z0.lwjgl.demo.render.renderer.outline.OutlineRenderer;
import org.a4z0.lwjgl.demo.shader.Shaders;
import org.a4z0.lwjgl.demo.util.Input;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public final class Main {

    public static long GL_WINDOW;
    public static int GL_WINDOW_WIDTH = 800, GL_WINDOW_HEIGHT = 600;

    public static final boolean GL_VSYNC = false;

    public static boolean PAUSED;
    public static boolean CLOSE;

    public static OutlineRenderer OUTLINE_RENDERER;

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        glfwWindowHint(GLFW_SAMPLES, 16);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        GL_WINDOW = glfwCreateWindow(GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT, "LWJGL", 0, 0);

        if(GL_WINDOW == 0)
            throw new IllegalStateException("Unable to create a Window!");

        glfwMakeContextCurrent(GL_WINDOW);
        glfwSwapInterval(GL_VSYNC ? 1 : 0);

        GL.createCapabilities();

        System.out.println("[Game]: -> \"Pre.Load\".");

        Shaders.init();
        Game.init();

        Game.LEVEL.getChunkAt(0, 0, 0);

        OUTLINE_RENDERER = new OutlineRenderer();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        // glEnable(GL_CULL_FACE);
        // glCullFace(GL_BACK);
        glLineWidth(1f);

        //noinspection resource
        glfwSetFramebufferSizeCallback(GL_WINDOW, (window, width, height) -> {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);

            GL_WINDOW_WIDTH = width;
            GL_WINDOW_HEIGHT = height;
        });

        glfwSetCursorPosCallback(GL_WINDOW, (window, x, y) -> Game.onMouse(x, y));

        System.out.println("[Game]: -> \"Post.Load\".");

        while(!glfwWindowShouldClose(GL_WINDOW) && !CLOSE) {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            Input.update();
            glfwPollEvents();

            if(!PAUSED) {
                Game.PLAYER.tick();
            }

            if(Input.isKeyPressed(GLFW_KEY_P))
                PAUSED = !PAUSED;

            Shaders.VOXEL_SHADER_PROGRAM.bind();
            Shaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_projection", Game.CAMERA.getProjection());
            Shaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_view", Game.CAMERA.getView());

            Game.LEVEL.tick();

            Shaders.VOXEL_SHADER_PROGRAM.unbind();

            glFlush();
            glfwSwapBuffers(GL_WINDOW);
        }

        System.out.println("[Game]: -> \"Close\".");

        glfwTerminate();
        System.exit(0);
    }
}