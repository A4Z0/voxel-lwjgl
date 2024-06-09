package org.a4z0.lwjgl.demo.voxel.server;

public class ServerWaterResistant extends Server {

    /**
    * Construct a {@link ServerWaterResistant}.
    */

    public ServerWaterResistant() {
        this(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
    }

    /**
    * Construct a {@link ServerWaterResistant}.
    *
    * @param i ...
    * @param p ...
    */

    public ServerWaterResistant(String i, int p) {

        // Water_Resistant-0.0.1-DEV
        super(i, p, "Water_Resistant", "0.0.1-DEV");
    }

    @Override
    public void tick() {

    }

    @Override
    public void start() {
        System.out.println("[Server:" + this.getName() +"]: Started!");
    }

    @Override
    public void reload() {
        System.out.println("[Server:" + this.getName() +"]: Reloaded!");
    }

    @Override
    public void restart() {
        System.out.println("[Server:" + this.getName() +"]: Restarted!");
    }

    @Override
    public void shutdown() {
        System.out.println("[Server:" + this.getName() +"]: Shutdown!");
    }
}