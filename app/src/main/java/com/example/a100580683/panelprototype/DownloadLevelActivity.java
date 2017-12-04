package com.example.a100580683.panelprototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 100580683 on 12/4/2017.
 */

public class DownloadLevelActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


    }

    public void download(View source)
    {

        EditText inputText;

        inputText = (EditText)findViewById(R.id.urlEditText);

        DownloadImageTask downloadableImage = new DownloadImageTask();

        downloadableImage.execute(inputText.getText().toString());

        //Get image upon completion, then go to preview screen?
        downloadableImage.getImage();
    }

    public void cancel(View source)
    {
        finish();
    }
}
