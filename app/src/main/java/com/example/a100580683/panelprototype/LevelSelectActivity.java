package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 100585588 on 12/3/2017.
 */

public class LevelSelectActivity extends AppCompatActivity {
    private HighscoreDB scoreDB;

    final int QCODE_SELECT = 100;
    final int QCODE_SCORE = 200;
    final int QCODE_PLAY = 300;
    final int RCODE_START = 101;
    final int RCODE_SCORE = 102;
    final int RCODE_CANCEL = -1;

    int[] levels;
    int[] buttons;

    int levelSelected = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //Array of every level image file
        levels = new int[]
                {
                        R.raw.level1, R.raw.level2, R.raw.level9, R.raw.level10,
                        R.raw.level4, R.raw.level11, R.raw.level3, R.raw.level5,
                        R.raw.level7, R.raw.level8, R.raw.level12, R.raw.level6
                };

        buttons = new int[]{
                R.id.Btnlvl1, R.id.Btnlvl2, R.id.Btnlvl3, R.id.Btnlvl4,

                R.id.Btnstart, R.id.Btnlvl6, R.id.Btnlvl7, R.id.Btnlvl8,

                R.id.Btnlvl9, R.id.Btnlvl10, R.id.Btnlvl11,R.id.Btnlvl12
        };

        scoreDB = new HighscoreDB(this);
        updateButtons();
    }

    public void selectLevel(View view) {
        //With multiple levels, put them in a list and use the index to choose the
        //level value, like with the in-game tile select

        Button btn = (Button) findViewById(view.getId());

        levelSelected = Integer.parseInt(btn.getText().toString());

        previewLevel();
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        //Selecting a level brings up the preview screen, which has three buttons

        //Inital case. Intent opened to preview level
        if (requestCode == QCODE_SELECT) {

            //If the user selected "Play"
            if (responseCode == RCODE_START) {
                //Start the game
                SoundManager.playSound(0);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("LevelImage", levels[levelSelected - 1]);
                startActivityForResult(intent, QCODE_PLAY);

                //If the user selected "Scores"
            } else if (responseCode == RCODE_SCORE) {
                SoundManager.playSound(3);

                //Only display the score intent if scores exist
                if (scoreDB.getScore(levelSelected) != null) {
                    SoundManager.playSound(0);
                    Intent intent = new Intent(this, ScoreActivity.class);
                    intent.putExtra("Scores", scoreDB.getScore(levelSelected));
                    startActivityForResult(intent, QCODE_SCORE);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.noscore, Toast.LENGTH_SHORT);
                    toast.show();
                    previewLevel();
                }
            }

            //From the Score intent...
        } else if (requestCode == QCODE_SCORE) {

            //Delete scores for the selected level if the user wanted to
            if (responseCode != 0) {
                SoundManager.playSound(2);
                scoreDB.clearLevel(levelSelected);
            }

            //When the score is closed, re-open the level preview
            previewLevel();

            //After the user plays a level...
        } else if (requestCode == QCODE_PLAY) {

            //Add a highscore if the user completed the level
            if (responseCode != 0) {
                SoundManager.playSound(4);
                scoreDB.createScore(levelSelected, responseCode);
                Toast toast = Toast.makeText(getApplicationContext(), "Level completed in " + responseCode + " " + " turns!", Toast.LENGTH_SHORT);
                toast.show();

            }
        }

        //Update button images
        updateButtons();
    }

    void previewLevel() {
        SoundManager.playSound(3);
        Intent intent = new Intent(this, LevelPreviewActivity.class);
        intent.putExtra("LevelNo", levelSelected);
        intent.putExtra("LevelImage", levels[levelSelected - 1]);
        startActivityForResult(intent, QCODE_SELECT);
    }


    public void goToDownload(View source) {
        SoundManager.playSound(3);
        Intent intent = new Intent(this, DownloadLevelActivity.class);
        startActivity(intent);
    }

    void updateButtons() {

        //Check every level
        for (int i = 0; i < levels.length; i++) {
            Button check = (Button) findViewById(buttons[i]);

            //If at least one score exists, the level has been completed
            if (scoreDB.getScore(i + 1) != null) {
                check.setBackground(getResources().getDrawable(R.drawable.buttonclear));
            } else {
                check.setBackground(getResources().getDrawable(R.drawable.buttonnew));
            }
        }
    }
}
