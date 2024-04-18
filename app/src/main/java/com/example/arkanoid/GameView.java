package com.example.arkanoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import com.example.arkanoid.core.Collidable;
import com.example.arkanoid.core.Counter;
import com.example.arkanoid.core.HitListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View {
    private static final long UPDATE_MILLIS = 30;
    public static int DWIDTH;
    public static int DHEIGHT;
    Context context;
    Ball ball;
    GameEnvironment gameEnv;
    Runnable runnable;
    Handler handler;
    int dWidth, dHeight;
    //Brick bricks[] = new Brick[24];
    List<View> sprites;
    BrickRemover brickRemover;
    BallRemover ballRemover;
    private Paddle paddle;
    private Counter brickCounter;
    private Counter ballCounter;
    private boolean backToMain = true;
    private LevelInformation levelInformation;
    public GameView(Context context, LevelInformation levelInformation) {
        super(context);
        this.context = context;
        this.levelInformation = levelInformation;
        this.sprites = new ArrayList<>();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        brickCounter = new Counter(0);
        brickRemover = new BrickRemover(this, this.brickCounter);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        DWIDTH = dWidth;
        DHEIGHT = dHeight;

        if (levelInformation.getBackground() != null) {
            sprites.add(levelInformation.getBackground());
        }

        com.example.arkanoid.geometry.Point center = new com.example.arkanoid.geometry.Point(size.x / 2, size.y / 2);
        gameEnv = new GameEnvironment();

        for (int i=0; i<levelInformation.numberOfBalls(); i++) {
            ball = new Ball(context,center);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.setGameEnvironment(gameEnv);
            sprites.add(ball);
        }


        /*
        ball = new Ball(context, center);
        ball.setGameEnvironment(gameEnv);
        ball.setVelocity(new Velocity(15,-15));
        sprites.add(ball);
        */
        ballCounter = new Counter(levelInformation.numberOfBalls());
        ballRemover = new BallRemover(this,ballCounter);
        createBricks();
        createEdges();
        int paddleWidth = levelInformation.paddleWidth();
        this.paddle = new Paddle(context, dWidth/2 - paddleWidth/2, dHeight - 400, paddleWidth, 50);
        sprites.add(paddle);
        gameEnv.addCollidable(paddle);
    }

    private void createBricks() {
        /*
        int numBricks = 0;
        int brickWidth = dWidth / 8;
        int brickHeight = dHeight / 16;
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 1; row++) {
                bricks[numBricks++] = new Brick(context,column*brickWidth, row*brickHeight, brickWidth, brickHeight);
                gameEnv.addCollidable(bricks[numBricks-1]);
                sprites.add(bricks[numBricks-1]);
                bricks[numBricks-1].addHitListener(brickRemover);
                this.brickCounter.increase(1);
            }
        }

         */
        for (Brick brick: levelInformation.bricks()) {
            gameEnv.addCollidable(brick);
            sprites.add(brick);
            brick.addHitListener(brickRemover);
            this.brickCounter.increase(1);
        }
    }

    private void createEdges() {
        Brick up = new Brick(context, -20, -20, dWidth+40, 20);
        Brick left = new Brick(context, -20, -20, 20, dHeight+40);
        Brick right = new Brick(context, dWidth, -20, 20, dHeight+40);
        Brick bottom = new Brick(context, -20, dHeight, dWidth+40, 20);
        sprites.add(up);
        sprites.add(left);
        sprites.add(right);
        sprites.add(bottom);
        gameEnv.addCollidable(up);
        gameEnv.addCollidable(right);
        gameEnv.addCollidable(left);
        gameEnv.addCollidable(bottom);
        bottom.addHitListener(ballRemover);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        for (int i=0; i<sprites.size(); i++) {
            sprites.get(i).draw(canvas);
        }

        if (this.brickCounter.getValue() <= 0) {
            backToMain();
        }

        if (this.ballCounter.getValue() <= 0) {
            backToMain();
        }

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        if (y >= paddle.getPaddleY() - dHeight/10) {
            paddle.onTouchEvent(event);
        }
        return true;
    }

    public void backToMain() {
        if (!backToMain)
            return;
        backToMain = false;
        // Start MainActivity
        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
        // Finish the current activity (GameView)
        ((Activity)getContext()).finish();
    }

    private void launchGameOver() {
        /*
        handler.removeCallbacksAndMessages(null);
        Intent intent = new Intent(context, GameOver.class);
        intent.putExtra("points", points);
        context.startActivity(intent);
        ((Activity) context).finish();

         */
    }

    private int xVelocity() {
        int[] values = {-35, -30, -25, 25, 30, 35};
        Random random = new Random();
        int index = random.nextInt();
        return values[index];
    }

    public void removeCollidable(Collidable c) {
        this.gameEnv.getCollidables().remove(c);
    }

    public void removeSprite(View s) {
        this.sprites.remove(s);
    }

    public int getdWidth() {
        return this.dWidth;
    }

    public int getdHeight() {
        return this.dHeight;
    }
}
