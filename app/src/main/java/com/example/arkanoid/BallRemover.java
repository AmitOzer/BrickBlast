package com.example.arkanoid;


import com.example.arkanoid.core.Counter;
import com.example.arkanoid.core.HitListener;

/**
 * listener that helps us to remove a ball from a game.
 *
 * @author Amit Ozer
 */
public class BallRemover implements HitListener {
    private GameView gameView;
    private Counter remainingBalls;


    public BallRemover(GameView gameView, Counter remainingBalls) {
        this.gameView = gameView;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Brick beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameView);

        this.remainingBalls.decrease(1);
    }
}

