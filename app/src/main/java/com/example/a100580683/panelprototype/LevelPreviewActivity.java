package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 100585588 on 12/3/2017.
 */

public class LevelPreviewActivity extends AppCompatActivity {
    final int QCODE_SELECT = 100;
    final int RCODE_START = 101;
    final int RCODE_SCORE = 102;
    final int RCODE_CANCEL = -1;

    int levelID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Bundle data = getIntent().getExtras();


        levelID = data.getInt("LevelImage");

        ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setImageResource(levelID);
    }

    public void startLevel(View view) {
        Intent result = new Intent(Intent.ACTION_PICK);


        setResult(RCODE_START);
        finish();
    }

    public void viewScores(View view) {
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(RCODE_SCORE);
        finish();
    }

    public void cancelSelect(View view) {
        SoundManager.playSound(2);
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(-1);
        finish();
    }
}
