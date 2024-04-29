package org.a4z0.lwjgl.demo.voxel.server;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;

import java.util.HashMap;
import java.util.Map;

/**
* ...
*/

public class InternalServer {

    protected final Map<String, LevelAccess> LEVELS = new HashMap<>();

    /**
    * Construct a {@link InternalServer}.
    */

    public InternalServer() {

    }

    /**
    * ...
    *
    * @param name ...
    *
    * @return ...
    */

    public LevelAccess getLevel(String name) {
        return this.LEVELS.get(name);
    }


}