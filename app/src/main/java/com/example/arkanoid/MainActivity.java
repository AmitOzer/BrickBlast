package com.example.arkanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button selectlevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.selectlevels = findViewById(R.id.selectlevels);
        this.selectlevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Levels.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    public void startGame(View view) {
        LevelInformation levelInformation = new BasicLvl(this);
        GameView gameView = new GameView(this, levelInformation);
        setContentView(gameView);
    }

     */
}