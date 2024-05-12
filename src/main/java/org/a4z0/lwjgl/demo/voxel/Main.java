package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.voxel.camera.PerspectiveCamera;
import org.a4z0.lwjgl.demo.voxel.level.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.voxel.level.chunk.InternalChunkProvider;
import org.a4z0.lwjgl.demo.voxel.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.voxel.gl.input.Input;
import org.a4z0.lwjgl.demo.voxel.gl.render.OutlineRenderer;
import org.a4z0.lwjgl.demo.voxel.gl.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.level.chunk.layer.ChunkLayer;
import org.a4z0.lwjgl.demo.voxel.level.chunk.layer.ChunkLayers;
import org.a4z0.lwjgl.demo.voxel.level.InternalLevel;
import org.a4z0.lwjgl.demo.voxel.math.Location;
import org.a4z0.lwjgl.demo.voxel.math.Vector3f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class Main {

    public static long GL_WINDOW;

    public static int GL_WINDOW_WIDTH = 800;
    public static int GL_WINDOW_HEIGHT = 600;

    public static boolean GL_VSYNC = false;

    @Deprecated
    static OutlineRenderer OUTLINE_RENDERER;

    @Deprecated
    static ChunkProvider TEST_CHUNK_PROVIDER = new InternalChunkProvider();

    @Deprecated
    public static InternalLevel TEST_LEVEL = new InternalLevel(0L, TEST_CHUNK_PROVIDER);

    @Deprecated
    static List<ChunkLayer> TEST_LAYERS = new ArrayList<>();

    static {
        Random R = new Random();

        for(int x = -16; x < 32; x++) {
            for(int y = 0; y < 16; y++) {
                for(int z = -16; z < 32; z++) {
                    TEST_LEVEL.getBlockAt(x, y, z).setColor(R.nextInt(255) + 1, R.nextInt(255) + 1, R.nextInt(255) + 1);
                }
            }
        }
    }

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

        // Loaders
        VGShaders.init();

        OUTLINE_RENDERER = new OutlineRenderer();

        Game.getInstance().setPlayerCamera(new PerspectiveCamera());
        Game.getInstance().setPlayer(new EntityPlayer("A4Z0", TEST_LEVEL, 0f, 60f, 0f));

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        /*glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);*/
        glLineWidth(1f);

        glfwSetFramebufferSizeCallback(GL_WINDOW, (window, width, height) -> {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);

            GL_WINDOW_WIDTH = width;
            GL_WINDOW_HEIGHT = height;
        });

        glfwSetInputMode(GL_WINDOW, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPosCallback(GL_WINDOW, (window, x, y) -> {
            Game.getInstance().tickPlayerDir((float) x, (float) y);
        });

        while(!glfwWindowShouldClose(GL_WINDOW)) {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            Input.update();
            glfwPollEvents();

            // Refresh
            if(Input.isKeyPressed(GLFW_KEY_R))
                for(ChunkLayers layer : Game.LAYERS)
                    for(ChunkLayer layerLayer : layer.getLayers())
                        layerLayer.delete(true);

            // Game Tick.
            Game.getInstance().tick();

            Matrix4f Projection = Game.getInstance().getPlayerCamera().getProjection();
            Matrix4f View = Game.getInstance().getPlayerCamera().getView();

            VGShaders.VOXEL_SHADER_PROGRAM.bind();
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_projection", Projection);
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_view", View);

            // Game Renderer
            Game.getInstance().render();

            VGShaders.VOXEL_SHADER_PROGRAM.unbind();

            VGShaders.OUTLINE_SHADER_PROGRAM.bind();
            VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_projection", Projection);
            VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_view", View);

            Location playerPos = Game.getInstance().getPlayer().getLocation();
            Vector3f center = Game.getInstance().getPlayer().getAABB().getCenter().add(playerPos.getPosition()).subtract(0.5f, 0.5f, 0.5f);

            OUTLINE_RENDERER.render(Game.getInstance().getPlayer().getAABB(), center.getX(), center.getY(), center.getZ(), 1f, 1f, 0f, 1f, 1f);

            VGShaders.OUTLINE_SHADER_PROGRAM.unbind();

            glFlush();
            glfwSwapBuffers(GL_WINDOW);
        }

        quit();
    }

    public static void quit() {
        glfwTerminate();

        System.exit(0);
    }
}