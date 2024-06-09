package org.a4z0.lwjgl.demo.voxel.world.server;

import org.a4z0.lwjgl.demo.voxel.server.Server;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.world.WorldLoader;

import java.util.ArrayList;
import java.util.List;

public class WorldLoaderServer extends WorldLoader {

    protected final Server s;
    protected final List<World> w = new ArrayList<>();

    /**
    * Construct a {@link WorldLoaderServer}.
    *
    * @param s ...
    */

    public WorldLoaderServer(Server s) {
        this.s = s;
    }

    /**
    * @return ...
    */

    public Server getServer() {
        return this.s;
    }
}