package com.example.arkanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }

    public void startLevel1(View view) {
        LevelInformation levelInformation = new BasicLvl(this);
        GameView gameView = new GameView(this, levelInformation);
        setContentView(gameView);
    }
    public void startLevel2(View view) {
        LevelInformation levelInformation = new Level2(this);
        GameView gameView = new GameView(this, levelInformation);
        setContentView(gameView);
    }
    public void startLevel3(View view) {
        LevelInformation levelInformation = new Level3(this);
        GameView gameView = new GameView(this, levelInformation);
        setContentView(gameView);
    }
}