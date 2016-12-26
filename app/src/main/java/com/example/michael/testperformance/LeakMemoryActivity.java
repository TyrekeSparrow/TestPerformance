package com.example.michael.testperformance;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by michael on 16-12-26.
 */

public class LeakMemoryActivity extends Activity {
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
    }

    public void allocateMemory(View view) {
        initImage();
    }

    private void initImage() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boat);
    }

    public void leakMemory(View view) {
        // leak situations
        // 1 leak memory by block worker threads
        leakMemoryByBlockWorkerThreads();
    }

    private void leakMemoryByBlockWorkerThreads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("performanceMemory", "worker thread start sleep");
                ThreadUtil.sleepAWhile(19 * 1000);
                Log.i("performanceMemory", "worker thread stop sleep");
            }
        }).start();
    }


}
