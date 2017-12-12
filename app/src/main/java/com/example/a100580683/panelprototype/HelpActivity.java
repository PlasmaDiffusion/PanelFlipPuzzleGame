package com.example.a100580683.panelprototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 100585588 on 12/2/2017.
 */

public class HelpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void closeTutorial(View view) {
        SoundManager.playSound(2);
        finish();
    }
}
