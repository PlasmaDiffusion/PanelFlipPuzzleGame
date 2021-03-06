package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class MainActivity extends AppCompatActivity {

    private int[] panelIDs;// = new int[25];
    private String winLayout; // x = off (blue), o = on (orange), - = doesn't matter
    private TextView turnDisplay;
    private int turns = 0;

    private int level = 0;
    private boolean downloaded = false;

    private Bitmap downloadedImage;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPanels();
        turnDisplay = (TextView) findViewById(R.id.Lblturns);

        Intent callingIntent = getIntent();

        level = callingIntent.getIntExtra("LevelImage", 0);

        downloaded = callingIntent.getBooleanExtra("downloaded", false);



        if (downloaded) {
            byte[] byteArray = getIntent().getByteArrayExtra("Image");
            downloadedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            //Downloaded levels get a different background
            ConstraintLayout relativeLayout = (ConstraintLayout) findViewById(R.id.levelBackground);
            relativeLayout.setBackground(getResources().getDrawable(R.drawable.diamond_background_gradient));
            relativeLayout.setBackgroundTintList(null);
        }

        loadLevel(level);


    }


    private void loadLevel(int levelImageID) {

        Bitmap levelImage;

        //Either get an image from a resource, or get a downloaded image sent from a parcel
        if (downloaded) levelImage = downloadedImage;
        else levelImage = BitmapFactory.decodeResource(getResources(), levelImageID);

        //Level image is the image we'll reference to build the level
        levelImage = Bitmap.createScaledBitmap(levelImage, 50, 110, false);


        //Assign target reference image
        Bitmap targetImage = Bitmap.createBitmap(levelImage, 0, 0, 50, 50);

        ImageView image = (ImageView) findViewById(R.id.targetImage);
        image.setImageBitmap(targetImage);


        final int onColour = 255 + 106 + 0;
        final int offColour = 0 + 148 + 255;

        winLayout = "";
        //Read top half of image for level win layout
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                int pixel = levelImage.getPixel((j * 10) + 5, (i * 10) + 5);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                Log.i("pixel RGB: ", Integer.toString(redValue) + " " + Integer.toString(greenValue) + " " + Integer.toString(blueValue));

                //The colour becomes a string
                if (onColour == redValue + blueValue + greenValue) winLayout += "o";
                else if (offColour == redValue + blueValue + greenValue) winLayout += "x";
                else winLayout += "-";
            }

        }

        Log.i("win string: ", winLayout);

        ToggleButton panel;

        String startingLayout = "";

        //Read bottom half for starting level layout
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {


                int pixel = levelImage.getPixel(5 + (j * 10), 5 + (i + 6) * 10);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                if (onColour == redValue + blueValue + greenValue) startingLayout += "o";
                else startingLayout += "x";


            }

        }

        //Now modify panels according to the level layout
        for (int i = 0; i < 25; i++) {

            panel = (ToggleButton) findViewById(panelIDs[i]);

            //Detect if its an orange panel
            if (startingLayout.charAt(i) == 'o') {

                //panel.setBackgroundColor(Color.parseColor("#f69256"));

                panel.toggle();

            } else //If not its blue
                //panel.setBackgroundColor(Color.parseColor("#1d4e89"));

                //Set win condition indicator
                if (winLayout.charAt(i) == 'x')
                    panel.setTextColor(getResources().getColor(R.color.colorOffLight));
                else if (winLayout.charAt(i) == 'o')
                    panel.setTextColor(getResources().getColor(R.color.colorOnDark));

                else { //Panels will have no text on them
                    panel.setText("");
                    panel.setTextOn("");
                    panel.setTextOff("");
                }
        }
    }

    public void initPanels() {
        ToggleButton panel;

        panelIDs = new int[]{R.id.tgl_11, R.id.tgl_12, R.id.tgl_13, R.id.tgl_14, R.id.tgl_15,
                R.id.tgl_21, R.id.tgl_22, R.id.tgl_23, R.id.tgl_24, R.id.tgl_25,
                R.id.tgl_31, R.id.tgl_32, R.id.tgl_33, R.id.tgl_34, R.id.tgl_35,
                R.id.tgl_41, R.id.tgl_42, R.id.tgl_43, R.id.tgl_44, R.id.tgl_45,
                R.id.tgl_51, R.id.tgl_52, R.id.tgl_53, R.id.tgl_54, R.id.tgl_55};


        //Set panels to whatever colour they should be and also display the win requirements.
        for (int i = 0; i < 25; i++) {
            panel = (ToggleButton) findViewById((panelIDs[i]));
            panel.setChecked(false);
            panel.setText("");
            panel.setTextOn("");
            panel.setTextOff("");

        }
    }

    public void flipPanel(View source) {
        SoundManager.playSound(1);
        int clickedPanelID = source.getId();

        int clickedPanelNumber = 0;

        //Determine which button was just pressed
        for (int i = 0; i < panelIDs.length; i++) {
            if (panelIDs[i] == clickedPanelID) {
                clickedPanelNumber = i;
                break;

            }

        }



        ToggleButton panel;
        //Toggle other buttons in a + pattern

        int startOfRow = clickedPanelNumber - (clickedPanelNumber % 5);

        //Toggle buttons horizontally
        for (int i = startOfRow; i < startOfRow + 5; i++) {

            //Skip if already flipped
            if (i == clickedPanelNumber) continue;

            panel = (ToggleButton) findViewById(panelIDs[i]);
            panel.toggle();
        }

        //Toggle buttons vertically
        for (int i = 0 + (clickedPanelNumber % 5); i < 25; i += 5) {
            //Skip if already flipped
            if (i == clickedPanelNumber) continue;

            panel = (ToggleButton) findViewById(panelIDs[i]);
            panel.toggle();
        }

        for (int i = 0; i < 25; i++) {
            panel = (ToggleButton) findViewById(panelIDs[i]);

            if (panel.isChecked()) {
                //panel.setBackgroundColor(Color.parseColor("#f69256"));

            } else {
                //panel.setBackgroundColor(Color.parseColor("#1d4e89"));
            }
        }

        turns++;

        turnDisplay.setText("Turns:" + turns);

        if (checkIfWon()) endGame(source);

    }

    private boolean checkIfWon() {
        ToggleButton panel;
        for (int i = 0; i < 25; i++) {
            //Do nothing if this panel doesn't matter
            if (winLayout.charAt(i) == '-') continue;

            panel = (ToggleButton) findViewById(panelIDs[i]);

            if (!panel.isChecked() && winLayout.charAt(i) == 'x') continue;
            else if (panel.isChecked() && winLayout.charAt(i) == 'o') continue;
            else return false; //Game hasn't been won yet


        }

        //If made through the above, ya won
        return true;
    }


    public void endGame(View view) {
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(turns);
        finish();
    }

    public void resetGame(View view) {
        turns = 0;
        turnDisplay.setText("Turns:" + turns);
        initPanels();
        loadLevel(level);
    }

    public void quitGame(View view) {
        Intent result = new Intent(Intent.ACTION_PICK);
        setResult(0);
        finish();
    }

}
