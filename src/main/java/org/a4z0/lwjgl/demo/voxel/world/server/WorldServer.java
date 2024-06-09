package org.a4z0.lwjgl.demo.voxel.world.server;

import org.a4z0.lwjgl.demo.voxel.server.Server;
import org.a4z0.lwjgl.demo.voxel.world.World;

import java.util.UUID;

public class WorldServer extends World {

    protected final Server s;

    /**
    * Construct a {@link WorldServer}.
    *
    * @param s ...
    * @param u ...
    * @param n ...
    */

    public WorldServer(Server s, UUID u, String n) {
        super(u, n);

        this.s = s;
    }

    /**
    * @return the {@link Server}.
    */

    public Server getServer() {
        return this.s;
    }

    @Override
    public void tick() {

    }

    @Override
    public void save() {
        System.out.println("[World -> Server:" + this.getName() + "]: Saved!");
    }
}