package com.example.a100580683.panelprototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Scott on 12/2/2017.
 */

public class DownloadHelpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_help);
    }

    public void next(View view){
        SoundManager.playSound(2);
        setContentView(R.layout.activity_download_help2);
    }

    public void close(View view){
        SoundManager.playSound(2);
        finish();
    }
}
