package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.voxel.camera.Camera;
import org.a4z0.lwjgl.demo.voxel.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.voxel.gl.input.Input;
import org.a4z0.lwjgl.demo.voxel.level.chunk.layer.ChunkLayer;
import org.a4z0.lwjgl.demo.voxel.level.chunk.layer.ChunkLayers;
import org.a4z0.lwjgl.demo.voxel.math.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
* Game's Main class that can only be instanced once.
*/

public final class Game {

    private static Game INSTANCE = new Game();
    public static final ChunkLayers[] LAYERS = new ChunkLayers[9];

    static {
        new Thread(() -> {
            int i = 0;

            for(int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    LAYERS[i++] = new ChunkLayers(Main.TEST_LEVEL.getChunkAt(x * 16, z * 16));
                }
            }
        }).start();
    }

    private EntityPlayer player;
    private Camera playerCamera;


    private final float SENSITIVITY = 0.05f;

    private float LAST_MOUSE_X = 0f;
    private float LAST_MOUSE_Y = 0f;

    /**
    * Construct a {@link Game}.
    */

    public Game() {
        if(INSTANCE != null)
            throw new IllegalAccessError("Unable to instantiate another Game!");

        INSTANCE = this;
    }

    /**
    * @return the current {@link EntityPlayer} or null.
    */

    public EntityPlayer getPlayer() {
        return this.player;
    }

    /**
    * Sets the current {@link EntityPlayer}.
    *
    * @param p {@link EntityPlayer} or null.
    */

    public void setPlayer(final EntityPlayer p) {
        this.player = p;
    }

    /**
     *
     * @return ...
     */

    public Camera getPlayerCamera() {
        return playerCamera;
    }

    /**
    * ...
    *
    * @param playerCamera ...
    */

    public void setPlayerCamera(Camera playerCamera) {
        this.playerCamera = playerCamera;
    }

    /**
    * ...
    */

    public void render() {
        for(ChunkLayers layers : LAYERS) {
            for(ChunkLayer layer : layers.getLayers()) {
               layer.update();
               layer.render();
            }
        }
    }

    /**
    * Ticks the {@link EntityPlayer}'s Camera.
    */

    public void tickCamera() {
        if(this.playerCamera == null || this.player == null)
            return;

        this.playerCamera.getPosition().set(
            this.player.getLocation().getX(),
            this.player.getLocation().getY() + 1,
            this.player.getLocation().getZ()
        );

        this.playerCamera.setPitch(this.player.getLocation().getPitch());
        this.playerCamera.setYaw(this.player.getLocation().getYaw());
    }

    public void tickPlayerDir(float X, float Y) {
        this.player.getLocation().setYaw(this.player.getLocation().getYaw() + ((LAST_MOUSE_X - X) * -SENSITIVITY));
        this.player.getLocation().setPitch(this.player.getLocation().getPitch() - ((LAST_MOUSE_Y - Y) * SENSITIVITY));

        this.LAST_MOUSE_X = X;
        this.LAST_MOUSE_Y = Y;
    }

    public void tickPlayerMovement() {
        //Vector3f dir = this.player.getLocation().getDirection().normalize();
        double yaw = Math.toRadians(this.player.getLocation().getYaw());

        Vector3f dir = new Vector3f((float) Math.sin(-yaw), 0, (float) Math.cos(yaw));

        if(Input.isKeyDown(GLFW_KEY_W))
            this.player.getLocation().add(dir.getX() * this.player.getWalkSpeed(), 0, dir.getZ() * this.player.getWalkSpeed());
        if(Input.isKeyDown(GLFW_KEY_A))
            this.player.getLocation().add(dir.getZ() * this.player.getWalkSpeed(), 0, -dir.getX() * this.player.getWalkSpeed());
        if(Input.isKeyDown(GLFW_KEY_S))
            this.player.getLocation().subtract(dir.getX() * this.player.getWalkSpeed(), 0, dir.getZ() * this.player.getWalkSpeed());
        if(Input.isKeyDown(GLFW_KEY_D))
            this.player.getLocation().subtract(dir.getZ() * this.player.getWalkSpeed(), 0, -dir.getX() * this.player.getWalkSpeed());
    }

    /**
    * Ticks the Game.
    */

    public void tick() {
        tickCamera();

        if(!TimeTicker.FPS_60.get())
            return;

        player.tick();
        tickPlayerMovement();
    }

    /**
    * @return a {@link Game}'s Instance.
    */

    public static Game getInstance() {
        return INSTANCE;
    }
}