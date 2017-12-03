package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

/**
 * Created by 100585588 on 12/3/2017.
 */

public class LevelSelectActivity extends AppCompatActivity {

    final int QCODE_SELECT = 100;
    final int QCODE_SCORE = 200;
    final int RCODE_START = 101;
    final int RCODE_SCORE = 102;
    final int RCODE_CANCEL = -1;

    int levelSelected = -1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    public void selectLevel(View view){
        //With multiple levels, put them in a list and use the index to choose the
        //level value, like with the in-game tile select
        levelSelected = (int)(Math.random() * 100) % 10;


        Intent intent = new Intent(this, LevelPreviewActivity.class);
        intent.putExtra("LevelNo",levelSelected);
        startActivityForResult(intent, QCODE_SELECT);
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == QCODE_SELECT){
            if(responseCode == RCODE_START){
                //Once we get the gameplay working, start this activity for result
                //The activity will return the number of turns taken (Or -1 if they quit)
                Intent intent = new Intent(this, MainActivity.class);
                //Do something here to tell the MainActivity which level to load
                startActivity(intent);
            }
            else if(responseCode == RCODE_SCORE){
                //Later, send an array of scores from the SQLite database
                Intent intent = new Intent(this, ScoreActivity.class);
                startActivityForResult(intent, QCODE_SCORE);
            }
        }
        else if(requestCode == QCODE_SCORE) {
            //When the score is closed, re-open the level preview
            Intent intent = new Intent(this, LevelPreviewActivity.class);
            intent.putExtra("LevelNo", levelSelected);
            startActivityForResult(intent, QCODE_SELECT);
        }

    }
}
