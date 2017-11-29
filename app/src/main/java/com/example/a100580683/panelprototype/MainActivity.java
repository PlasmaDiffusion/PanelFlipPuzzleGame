package com.example.a100580683.panelprototype;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private int[] panelIDs;// = new int[25];
    private TextView turnDisplay;
    private int turns = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPanels();
        turnDisplay = (TextView)findViewById(R.id.txt_Turns);
    }

    public void initPanels()
    {
        ToggleButton panel;

        panelIDs = new int[]{R.id.toggleButton0, R.id.toggleButton1, R.id.toggleButton2, R.id.toggleButton3, R.id.toggleButton4,
                R.id.toggleButton5, R.id.toggleButton6, R.id.toggleButton7, R.id.toggleButton8, R.id.toggleButton9,
                R.id.toggleButton10, R.id.toggleButton11, R.id.toggleButton12, R.id.toggleButton13, R.id.toggleButton14,
                R.id.toggleButton15, R.id.toggleButton16, R.id.toggleButton17, R.id.toggleButton18, R.id.toggleButton19,
                R.id.toggleButton20, R.id.toggleButton21, R.id.toggleButton22, R.id.toggleButton23, R.id.toggleButton24};

        for(int i= 0; i < 25; i++){
            panel = (ToggleButton) findViewById((panelIDs[i]));
            panel.setText("X");
            panel.setTextOn("O");
            panel.setTextOff("X");

            panel = (ToggleButton)findViewById(panelIDs[i]);

            if(panel.isChecked()){
                panel.setBackgroundColor(Color.parseColor("#f69256"));

            }else{
                panel.setBackgroundColor(Color.parseColor("#1d4e89"));
            }
        }
    }

    public void flipPanel(View source)
    {
        int clickedPanelID = source.getId();

        int clickedPanelNumber = 0;

        //Determine which button was just pressed
        for (int i = 0; i <panelIDs.length; i++)
        {
            if (panelIDs[i] == clickedPanelID)
            {
                clickedPanelNumber = i;
                break;

            }

        }

        //Flip stuff in a + pattern
        //Log.d("lol", Integer.toString(clickedPanelNumber));


        ToggleButton panel;
        //Toggle other buttons in a + pattern

        int startOfRow = clickedPanelNumber - (clickedPanelNumber % 5);

        //Toggle buttons vertically
        for (int i = startOfRow; i < startOfRow + 5; i++)
        {

            //Skip if already flipped
            if (i == clickedPanelNumber) continue;

            panel = (ToggleButton) findViewById(panelIDs[i]);
            panel.toggle();
        }

        //Toggle buttons vertically
        for (int i = 0 + (clickedPanelNumber % 5) ; i < 25; i+= 5)
        {
            //Skip if already flipped
            if (i == clickedPanelNumber) continue;

            panel = (ToggleButton) findViewById(panelIDs[i]);
            panel.toggle();
        }

        for(int i = 0; i < 25; i++){
            panel = (ToggleButton)findViewById(panelIDs[i]);

            if(panel.isChecked()){
                panel.setBackgroundColor(Color.parseColor("#f69256"));

            }else{
                panel.setBackgroundColor(Color.parseColor("#1d4e89"));
            }
        }

        turns++;

        turnDisplay.setText("Flips: " + turns);
    }

    public void endGame(View view){
        finish();
    }
}
