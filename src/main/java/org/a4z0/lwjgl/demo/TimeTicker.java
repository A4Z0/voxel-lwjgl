package org.a4z0.lwjgl.demo;

/**
* ...
*/

public class TimeTicker {

    public static final TimeTicker FPS_60 = new TimeTicker(60d);

    protected DeltaTime deltaTime;
    protected double targetFps;

    /**
    * Construct a {@link TimeTicker}.
    *
    * @param targetFPS Target FPS.
    */

    public TimeTicker(double targetFPS) {
        this.deltaTime = new DeltaTime();
        this.targetFps = targetFPS;
    }

    /**
    * @return ...
    */

    public DeltaTime getDeltaTime() {
        return this.deltaTime;
    }

    /**
    * @return ...
    */

    public boolean get() {
        double elapsedTime = deltaTime.getElapsedTime();
        double tickInterval = 1.0 / targetFps;

        if(elapsedTime >= tickInterval) {
            deltaTime.reset();

            return true;
        } else {
            return false;
        }
    }
}