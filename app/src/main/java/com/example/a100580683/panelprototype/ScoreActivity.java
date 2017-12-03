package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 100585588 on 12/2/2017.
 */

public class ScoreActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
    }

    public void closeScores(View view){
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(-1);
        finish();
    }

}
