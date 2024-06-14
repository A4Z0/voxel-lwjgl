package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.layer.ChunkLayer;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.legacy.done.camera.PerspectiveCamera;
import org.a4z0.lwjgl.demo.voxel.font.GLFont;
import org.a4z0.lwjgl.demo.voxel.level.server.LevelServer;
import org.a4z0.lwjgl.demo.voxel.math.AABBf;
import org.a4z0.lwjgl.demo.voxel.render.renderer.outline.OutlineRenderer;
import org.a4z0.lwjgl.demo.voxel.render.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.legacy.util.Input;
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
    public static GLFont GL_FONT;

    public static Level LEVEL;
    public static Chunk CHUNK;
    public static ChunkLayer[] CHUNK_RENDER_ARRAY = new ChunkLayer[1];

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

        VGShaders.init();

        GL_FONT = GLFont.create("assets/font/minecraft.ttf", 16);

        // TODO: Handle After Load.
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

        // Listen for Mouse.
        glfwSetCursorPosCallback(GL_WINDOW, (window, x, y) -> Game.onMouse(x, y));

        OUTLINE_RENDERER = new OutlineRenderer();

        System.out.println("[Game]: -> \"Post.Load\".");

        LEVEL = new LevelServer(null, null, null, 0L);
        CHUNK = LEVEL.getChunkAt(0, 0, 0);
        CHUNK.load();

        CHUNK_RENDER_ARRAY[0] = new ChunkLayer(CHUNK);

        Render render = new Render();

        while(!glfwWindowShouldClose(GL_WINDOW) && !CLOSE) {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            Input.update();

            glfwPollEvents();

            // Listen for Keyboard.
            //Game.onKeyboard();

            if(!PAUSED) {
                Game.LEVEL_SERVER.tick();

                //if(TimeTicker.FPS_60.get()) {
                    Game.PLAYER.tick();
                    glfwSetWindowTitle(GL_WINDOW, "Ground: " + Game.PLAYER.isOnGround());
                //}
            }

            if(Input.isKeyPressed(GLFW_KEY_R))
                for(ChunkLayer chunkRender : CHUNK_RENDER_ARRAY)
                    chunkRender.delete(true);

            render.render();

            AABBf SIXTEEN = new AABBf(0.5f, 0.5f, 0.5f).subtract(0.5f, 0.5f, 0.5f);

            // North (Red)
            OUTLINE_RENDERER.render(SIXTEEN.clone().add(1f, 0f, 0f), 0, 0, 0, 1f, 0f, 0f, 1f, 1f);

            glFlush();
            glfwSwapBuffers(GL_WINDOW);
        }

        System.out.println("[Game]: -> \"Close\".");

        glfwTerminate();
        System.exit(0);
    }

    public static class Render {

        public static final PerspectiveCamera CAMERA = new PerspectiveCamera();

        public void render() {
            CAMERA.getPosition().set(Game.PLAYER.getLocation()).add(0.5f, 2f, 0.5f);
            CAMERA.setPitch(Game.PLAYER.getLocation().getPitch());
            CAMERA.setYaw(Game.PLAYER.getLocation().getYaw());

            VGShaders.VOXEL_SHADER_PROGRAM.bind();
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_projection", CAMERA.getProjection());
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_view", CAMERA.getView());

            for(ChunkLayer CHUNK_RENDER : CHUNK_RENDER_ARRAY) {
                CHUNK_RENDER.render(true);
            }

            VGShaders.VOXEL_SHADER_PROGRAM.unbind();
        }
    }
}