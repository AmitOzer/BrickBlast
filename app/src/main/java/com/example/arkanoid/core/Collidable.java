package com.example.arkanoid.core;

import com.example.arkanoid.Ball;
import com.example.arkanoid.Velocity;
import com.example.arkanoid.geometry.Point;
import com.example.arkanoid.geometry.Rectangle;

/**
 * interface for Collidable objects (objects that can collide with a ball).
 *
 * @author Amit Ozer
 */
public interface Collidable {

    /**
     * return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter - the hitter ball
     * @param collisionPoint  - where the collision happen
     * @param currentVelocity - the velocity before the collision
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
