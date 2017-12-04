package com.example.a100580683.panelprototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

/**
 * Created by 100580683 on 12/4/2017.
 */

//Loads in an online bitmap of additional levels.

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
{
    private Bitmap image;

    public DownloadImageTask()
    {
        image = null;
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
        image = result;
    }

    public Bitmap getImage() {
        return image;
    }
}
