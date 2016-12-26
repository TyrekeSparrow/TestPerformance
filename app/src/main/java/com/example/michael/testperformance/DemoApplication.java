package com.example.michael.testperformance;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by michael on 16-12-23.
 */

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // install leakCanary in debug mode
        if (isDebuggable(this)) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
            Log.d("Canary", "leak canary install");
        }

        startBlockCanaryInDebugMode();

        // print memory loop
        printMemoryLoop();

    }

    private void printMemoryLoop() {
        MemoryUtil.printMemoryLoop();
    }

    private void startBlockCanaryInDebugMode() {
        boolean isDebuggable = isDebuggable(this.getApplicationContext());
        Log.d("Canary", "isDebuggable = " + isDebuggable);
        if (isDebuggable) {
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
    }

    public static boolean isDebuggable(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        boolean isDebuggable = (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebuggable;
    }

}
