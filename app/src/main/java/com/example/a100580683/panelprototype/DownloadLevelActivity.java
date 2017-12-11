package com.example.a100580683.panelprototype;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by 100580683 on 12/4/2017.
 */

public class DownloadLevelActivity extends AppCompatActivity
{
    final int QCODE_PLAY = 300;

    private DownloadImageTask downloadableImage;

    private Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        //Upon startup play button is disabled until the image was downloaded
        Button playButton = (Button)findViewById(R.id.playButton);

        playButton.setEnabled(false);
    }

    public void download(View source)
    {
        //Preview image
        ImageView image = (ImageView)findViewById(R.id.downloadedPreviewImageView);

        //Play button that will  be enabled
        Button playButton = (Button)findViewById(R.id.playButton);


        EditText inputText;

        inputText = (EditText)findViewById(R.id.urlEditText);
        String url = "https://i.imgur.com/" + inputText.getText().toString() + ".png";

        DownloadImageTask downloadableImage = new DownloadImageTask(image, playButton);

        downloadableImage.execute(url);


    }

    public void play(View source)
    {
        SoundManager.playSound(0);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("LevelImage", R.id.downloadedPreviewImageView);
        intent.putExtra("downloaded", true);


        //Decode bitmap to send it to level
        ImageView image = (ImageView)findViewById(R.id.downloadedPreviewImageView);

        image.setDrawingCacheEnabled(true);

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();



        //It was difficult transferring the bitmap to a new intent. I used a byte array method I got from this site:
        //http://www.jayrambhia.com/blog/pass-activity-bitmap
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] byteArray = bStream.toByteArray();
        intent.putExtra("Image", byteArray);

        startActivityForResult(intent, QCODE_PLAY);

    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == QCODE_PLAY && responseCode > 0){
            SoundManager.playSound(4);
            Toast toast = Toast.makeText(getApplicationContext(),"Level completed in " + responseCode + " " + " turns!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void openHelp(View source)
    {
        Intent intent = new Intent(this, DownloadHelpActivity.class);

        startActivity(intent);
    }

    public void cancel(View source)
    {
        SoundManager.playSound(2);
        finish();
    }
}
