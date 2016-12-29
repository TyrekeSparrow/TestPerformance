package com.example.michael.testperformance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void blockMainThread(View view) {
        blockMainThread();
    }

    private static final long time = 2 * 1000;

    private void blockMainThread() {
        // block situations
        // 1 main thread sleep
        ThreadUtil.sleepAWhile(time);
    }

    public void startLeakMemoryActivity(View view) {
        Intent intent = new Intent(this, LeakMemoryActivity.class);
        startActivity(intent);
    }

    public void test(View view) {
    }
}
