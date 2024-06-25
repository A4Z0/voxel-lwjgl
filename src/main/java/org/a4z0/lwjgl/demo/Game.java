package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.camera.FreeCamera;
import org.a4z0.lwjgl.demo.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.server.Server;
import org.a4z0.lwjgl.demo.server.ServerWaterResistant;

import java.util.UUID;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    public static Server SERVER;
    public static Level LEVEL;

    public static FreeCamera CAMERA;
    public static EntityPlayer PLAYER;

    public static void init() {
        SERVER = new ServerWaterResistant();
        LEVEL = new Level(SERVER, UUID.randomUUID(), "Hotdogão", 0);
        CAMERA = new FreeCamera();
        PLAYER = new EntityPlayer("A4Z0", LEVEL, 0, 0, 0);

        SERVER.getPlayers().add(PLAYER);
    }

    public static void tick() {
        CAMERA.getPosition().set(Game.PLAYER.getLocation()).add(0.5f, 2f, 0.5f);
        CAMERA.setPitch(Game.PLAYER.getLocation().getPitch());
        CAMERA.setYaw(Game.PLAYER.getLocation().getYaw());
    }


    private static boolean VISIBLE = true;

    private static float LAST_MOUSE_X = 0f;
    private static float LAST_MOUSE_Y = 0f;

    public static void onMouse(double x, double y) {
        if(!Main.PAUSED) {
            if(VISIBLE) {
                glfwSetInputMode(Main.GL_WINDOW, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

                VISIBLE = false;
            }

            final float SENSITIVITY = 0.05f;

            PLAYER.getLocation().setYaw((float) (PLAYER.getLocation().getYaw() + ((LAST_MOUSE_X - x) * -SENSITIVITY)));
            PLAYER.getLocation().setPitch((float) (PLAYER.getLocation().getPitch() - ((LAST_MOUSE_Y - y) * SENSITIVITY)));

            LAST_MOUSE_X = (float) x;
            LAST_MOUSE_Y = (float) y;
        } else {
            if(!VISIBLE) {
                glfwSetInputMode(Main.GL_WINDOW, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

                VISIBLE = true;
            }
        }
    }
}