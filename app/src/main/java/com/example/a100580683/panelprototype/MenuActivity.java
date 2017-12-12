package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 100585588 on 11/29/2017.
 */

public class MenuActivity extends AppCompatActivity {

    SoundManager sm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sm = new SoundManager();
        sm.initializePool(getResources());


    }

    public void startGame(View view) {
        SoundManager.playSound(0);
        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);
    }

    public void showTutorial(View view) {
        SoundManager.playSound(3);
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }


}
