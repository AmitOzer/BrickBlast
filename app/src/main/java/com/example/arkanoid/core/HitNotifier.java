package com.example.arkanoid.core;

/**
 * part of the observer pattern - the notifier.
 *
 * @author Amit Ozer
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - the hl (HitListener)
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - the hl (HitListener)
     */
    void removeHitListener(HitListener hl);
}