package org.a4z0.lwjgl.demo.voxel.legacy.geometry;

import org.joml.Matrix4f;

/**
* Represents a Frustum.
*/

public class Frustum {

    protected final Plane[] planes = new Plane[6];

    /**
    * Construct a {@link Frustum}.
    */

    public Frustum() {
        this(new Plane(1, 0, 0, 1), new Plane(-1, 0, 0, 1), new Plane(0, -1, 0, 1), new Plane(0, 1, 0, 1), new Plane(0, 0, -1, 1), new Plane(0, 0, 1, 1));
    }

    /**
    * Construct a {@link Frustum}.
    *
    * @param l Left {@link Plane}.
    * @param r Right {@link Plane}.
    * @param t Top {@link Plane}.
    * @param b Bottom {@link Plane}.
    * @param n Near {@link Plane}.
    * @param f Far {@link Plane}.
    */

    public Frustum(Plane l, Plane r, Plane t, Plane b, Plane n, Plane f) {
        this.planes[0] = l;
        this.planes[1] = r;
        this.planes[2] = t;
        this.planes[3] = b;
        this.planes[4] = n;
        this.planes[5] = f;
    }

    /*public void frustrate(Matrix4f projection, Matrix4f view) {
        Matrix4f projectionView = new Matrix4f(projection).mul(view);

        this.planes[0].set(projectionView.m03() + projectionView.m00(), projectionView.m13() + projectionView.m10(), projectionView.m23() + projectionView.m20()).setDistance(projectionView.m33() + projectionView.m30()).normalize();
        this.planes[1].set(projectionView.m03() - projectionView.m00(), projectionView.m13() - projectionView.m10(), projectionView.m23() - projectionView.m20()).setDistance(projectionView.m33() - projectionView.m30()).normalize();
        this.planes[2].set(projectionView.m03() + projectionView.m01(), projectionView.m13() + projectionView.m11(), projectionView.m23() + projectionView.m21()).setDistance(projectionView.m33() + projectionView.m31()).normalize();
        this.planes[3].set(projectionView.m03() - projectionView.m01(), projectionView.m13() - projectionView.m11(), projectionView.m23() - projectionView.m21()).setDistance(projectionView.m33() - projectionView.m31()).normalize();
        this.planes[4].set(projectionView.m03() + projectionView.m02(), projectionView.m13() + projectionView.m12(), projectionView.m23() + projectionView.m22()).setDistance(projectionView.m33() + projectionView.m32()).normalize();
        this.planes[5].set(projectionView.m03() - projectionView.m02(), projectionView.m13() - projectionView.m12(), projectionView.m23() - projectionView.m22()).setDistance(projectionView.m33() - projectionView.m32()).normalize();
    }*/

    /**
    * Translates this {@link Frustum}.
    *
    * @param matrix {@link Matrix4f Matrix} that will translate this {@link Frustum}.
    *
    * @return this {@link Frustum}.
    */

    public Frustum translate(Matrix4f matrix) {
        return this.translate(
            matrix.m03() + matrix.m00(), matrix.m13() + matrix.m10(), matrix.m23() + matrix.m20(),
            matrix.m03() - matrix.m00(), matrix.m13() - matrix.m10(), matrix.m23() - matrix.m20(),
            matrix.m03() + matrix.m01(), matrix.m13() + matrix.m11(), matrix.m23() + matrix.m21(),
            matrix.m03() - matrix.m01(), matrix.m13() - matrix.m11(), matrix.m23() - matrix.m21(),
            matrix.m03() + matrix.m02(), matrix.m13() + matrix.m12(), matrix.m23() + matrix.m22(),
            matrix.m03() - matrix.m02(), matrix.m13() - matrix.m12(), matrix.m23() - matrix.m22()
        );
    }

    /**
    * Translates this {@link Frustum}.
    *
    * @param lX Left {@link Plane} X-Axis.
    * @param lY Left {@link Plane} Y-Axis.
    * @param lZ Left {@link Plane} Z-Axis.
    * @param rX Right {@link Plane} X-Axis.
    * @param rY Right {@link Plane} Y-Axis.
    * @param rZ Right {@link Plane} Z-Axis.
    * @param tX Top {@link Plane} X-Axis.
    * @param tY Top {@link Plane} Y-Axis.
    * @param tZ Top {@link Plane} Z-Axis.
    * @param bX Bottom {@link Plane} X-Axis.
    * @param bY Bottom {@link Plane} Y-Axis.
    * @param bZ Bottom {@link Plane} Z-Axis.
    * @param nX Near {@link Plane} X-Axis.
    * @param nY Near {@link Plane} Y-Axis.
    * @param nZ Near {@link Plane} Z-Axis.
    * @param fX Far {@link Plane} X-Axis.
    * @param fY Far {@link Plane} Y-Axis.
    * @param fZ Far {@link Plane} Z-Axis.
    *
    * @return this {@link Frustum}.
    */

    public Frustum translate(
        float lX, float lY, float lZ,
        float rX, float rY, float rZ,
        float tX, float tY, float tZ,
        float bX, float bY, float bZ,
        float nX, float nY, float nZ,
        float fX, float fY, float fZ
    ) {
        this.getLeft().set(lX, lY, lZ);
        this.getRight().set(rX, rY, rZ);
        this.getTop().set(tX, tY, tZ);
        this.getBottom().set(bX, bY, bZ);
        this.getNear().set(nX, nY, nZ);
        this.getFar().set(fX, fY, fZ);

        return this;
    }

    /**
    * @return the left {@link Plane}.
    */

    public Plane getLeft() {
        return this.planes[0];
    }

    /**
    * @return the right {@link Plane}.
    */

    public Plane getRight() {
        return this.planes[1];
    }

    /**
    * @return the top {@link Plane}.
    */

    public Plane getTop() {
        return this.planes[2];
    }

    /**
    * @return the bottom {@link Plane}.
    */

    public Plane getBottom() {
        return this.planes[3];
    }

    /**
    * @return the near {@link Plane}.
    */

    public Plane getNear() {
        return this.planes[4];
    }

    /**
    * @return the far {@link Plane}.
    */

    public Plane getFar() {
        return this.planes[5];
    }

    /**
    * Analyzes if the coordinates resides inside the {@link Frustum}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Radius.
    *
    * @return true if the coordinates resides inside the {@link Frustum}, false otherwise.
    */

    public boolean resides(float x, float y, float z, float r) {
        for(Plane plane : this.planes)
            if(plane.distance(x, y, z) <= -r)
                return false;

        return true;
    }
}