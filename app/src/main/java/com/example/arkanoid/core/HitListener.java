package com.example.arkanoid.core;

import com.example.arkanoid.Ball;
import com.example.arkanoid.Brick;
/**
 * part of the observer - the listener.
 *
 * @author Amit Ozer
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * <p>
     * The hitter parameter is the Ball that's doing the hitting.
     * </p>
     *
     * @param beingHit - the block being hit (Block)
     * @param hitter   - the hitter (ball)
     */
    void hitEvent(Brick beingHit, Ball hitter);
}

