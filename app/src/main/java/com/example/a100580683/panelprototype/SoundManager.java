package com.example.a100580683.panelprototype;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by 100585588 on 12/5/2017.
 */

//Idea taken from https://stackoverflow.com/questions/16259727/passing-soundpool-to-multiple-activities-in-android

public class SoundManager extends Application {

    public static SoundPool soundManager = null;

    private static int soundFx[];

    private static  AssetFileDescriptor soundPaths[];

    public static SoundPool getPool(){
        return soundManager;
    }

    public void initializePool(Resources res) {
        SoundPool.Builder sBuilder = new SoundPool.Builder();
        sBuilder.setMaxStreams(20);
        soundManager = sBuilder.build();

        //Update this for more or less sounds
        soundFx = new int[7];
        soundPaths = new AssetFileDescriptor[7];

        soundPaths[0] = res.openRawResourceFd(R.raw.twinkle);
        soundFx[0] = soundManager.load(soundPaths[0],0);

        soundPaths[1] = res.openRawResourceFd(R.raw.flip);
        soundFx[1] = soundManager.load(soundPaths[1],0);
    }

    public static void playSound(int id){
        Log.i("Sound", Integer.toString(id));
        soundManager.play(soundFx[id], 1f, 1f,0, 0, 1f);
    }
}