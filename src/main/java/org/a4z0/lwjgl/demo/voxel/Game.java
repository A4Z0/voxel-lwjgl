package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.voxel.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.voxel.level.server.LevelServer;
import org.a4z0.lwjgl.demo.voxel.math.Vector3f;
import org.a4z0.lwjgl.demo.voxel.server.Server;
import org.a4z0.lwjgl.demo.voxel.server.ServerWaterResistant;
import org.a4z0.lwjgl.demo.voxel.world.server.WorldServer;
import org.a4z0.lwjgl.demo.voxel.legacy.util.Input;

import java.util.UUID;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    public static Server SERVER = new ServerWaterResistant();
    public static WorldServer WORLD_SERVER = new WorldServer(SERVER, null, null);
    public static LevelServer LEVEL_SERVER = new LevelServer(WORLD_SERVER, UUID.randomUUID(), null, 0L);

    public static EntityPlayer PLAYER = new EntityPlayer("A4Z0", LEVEL_SERVER, 0, 1, 0);

    /*static {
        Random r = new Random();

        for(int x = 0; x <= 15; x++) {
            for(int y = 0; y <= 15; y++) {
                for(int z = 0; z <= 15; z++) {
                    LEVEL_SERVER.getBlockAt(x, y, z).setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
                }
            }
        }
    }*/

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

    public static void onKeyboard() {
        onKeyboard(Math.toRadians(PLAYER.getLocation().getYaw()));
    }

    public static void onKeyboard(double y) {
        onKeyboard(new Vector3f((float) Math.sin(-y), 0, (float) Math.cos(y)));
    }

    public static void onKeyboard(Vector3f d) {
        if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) && Input.isKeyPressed(GLFW_KEY_X)) {
            Main.CLOSE = true;

            System.out.println("[Action]: Quit");
        }

        if(Input.isKeyPressed(GLFW_KEY_ESCAPE)) {
            Main.PAUSED = !Main.PAUSED;

            System.out.println("[Action]: Pause -> " + (Main.PAUSED ? "Frozen" : "Unfrozen") + "!");
        }

        if(!Main.PAUSED) {
            if (Input.isKeyDown(GLFW_KEY_W))
                PLAYER.getLocation().add(d.getX() * PLAYER.getWalkSpeed(), 0, d.getZ() * PLAYER.getWalkSpeed());
            if (Input.isKeyDown(GLFW_KEY_A))
                PLAYER.getLocation().add(d.getZ() * PLAYER.getWalkSpeed(), 0, -d.getX() * PLAYER.getWalkSpeed());
            if (Input.isKeyDown(GLFW_KEY_S))
                PLAYER.getLocation().subtract(d.getX() * PLAYER.getWalkSpeed(), 0, d.getZ() * PLAYER.getWalkSpeed());
            if (Input.isKeyDown(GLFW_KEY_D))
                PLAYER.getLocation().subtract(d.getZ() * PLAYER.getWalkSpeed(), 0, -d.getX() * PLAYER.getWalkSpeed());
        }
    }
}