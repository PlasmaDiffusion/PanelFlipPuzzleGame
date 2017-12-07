package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by 100585588 on 12/2/2017.
 */

public class ScoreActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Bundle retrieve = getIntent().getExtras();

        ListView scoreContainer = (ListView)findViewById(R.id.lstScores);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, retrieve.getStringArray("Scores"));
        scoreContainer.setAdapter(adapter);


    }

    public void closeScores(View view){
        SoundManager.playSound(3);
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(0);
        finish();
    }

    public void deleteScores(View view){
        SoundManager.playSound(2);
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(1);
        finish();
    }
}
