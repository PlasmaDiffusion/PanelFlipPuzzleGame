package com.example.a100580683.panelprototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by 100580683 on 12/4/2017.
 */

//Loads in an online bitmap of an extra/custom level. A play button will get enabled when this happens

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
{
    private ImageView image;
    private Button enabledButton;
    private Bitmap bitmap;

    public DownloadImageTask(ImageView image, Button enabledButton)
    {
        this.enabledButton = enabledButton;
        this.image = image;
    }

    protected Bitmap doInBackground(String... urls)
    {
        Bitmap newImage = null;
        String url = urls[0];

        try
        {
            InputStream inputStream = new java.net.URL(url).openStream();
            newImage = BitmapFactory.decodeStream(inputStream);
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }

        return newImage;
    }

    protected void onPostExecute(Bitmap result)
    {
        //Make the image appear, along with a play button
        if (result != null)
        {
            bitmap = result;
            image.setImageBitmap(bitmap);
            enabledButton.setEnabled(true);
            Log.i("image downloading", "downloaded it");
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
