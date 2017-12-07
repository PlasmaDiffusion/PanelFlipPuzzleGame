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
//Button sound effects from https://www.youtube.com/watch?v=2o11YM2Mwjc
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
        soundFx = new int[5];
        soundPaths = new AssetFileDescriptor[5];

        soundPaths[0] = res.openRawResourceFd(R.raw.btn_forward);
        soundFx[0] = soundManager.load(soundPaths[0],0);

        soundPaths[1] = res.openRawResourceFd(R.raw.flip);
        soundFx[1] = soundManager.load(soundPaths[1],0);

        soundPaths[2] = res.openRawResourceFd(R.raw.btn_back);
        soundFx[2] = soundManager.load(soundPaths[2],0);

        soundPaths[3] = res.openRawResourceFd(R.raw.btn_general);
        soundFx[3] = soundManager.load(soundPaths[3],0);

        soundPaths[4] = res.openRawResourceFd(R.raw.twinkle);
        soundFx[4] = soundManager.load(soundPaths[4],0);
    }

    public static void playSound(int id){
        soundManager.play(soundFx[id], 1f, 1f,0, 0, 1f);
    }
}
