package com.example.michael.testperformance;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void blockMainThread(View view) {
        blockMainThread();
    }

    private void blockMainThread() {
        // block situations
        // 1 main thread sleep
        ThreadUtil.sleepAWhile(1 * 1000);
    }

    public void startLeakMemoryActivity(View view) {
        Intent intent = new Intent(this, LeakMemoryActivity.class);
        startActivity(intent);
    }
}
