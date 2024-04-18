// 216498691 Amit Ozer
package com.example.arkanoid.core;


import com.example.arkanoid.geometry.Point;

/**
 * Holding information about a collision, defined by a point and a collidable
 * object.
 * @author Amit Ozer
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    /**
     * regular constructor.
     * @param point - the collision point (Point)
     * @param collidable - the collidable object (Collidable)
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = new Point(point);
        this.collidable = collidable;
    }

    /**
     * returns the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return new Point(this.point);
    }

    /**
     * returns the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}