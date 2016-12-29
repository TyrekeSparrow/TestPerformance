package com.example.michael.testperformance;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by michael on 16-12-23.
 */

public class PerformanceApplication extends Application {
    private static final boolean DEBUG = true;
    // private static final boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();

        /*
        // install leakCanary in debug mode
        if (DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
            Log.d("performance", "leak canary install");
        }
        */

        installLeakCanaryInDebugMode();

        startBlockCanaryInDebugMode();

        // print memory loop
        printMemoryLoop();

    }

    private void installLeakCanaryInDebugMode() {
        // only available n debug mode
        // class found
        // invoke by reflect
        Log.d("performance", "installLeakCanaryInDebugMode DEBUG = " + DEBUG);
        if (DEBUG) {
            boolean classFound = false;
            try {
                Class leakCanaryClass = Class.forName("com.squareup.leakcanary.LeakCanary");
                if (leakCanaryClass != null) {
                    classFound = true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Log.d("performance", "LeakCanary classFound = " + classFound);
            if (classFound) {
                // normal invoke code
                // LeakCanary.install(this);

                // reflect invoke code
                installLeakCanaryByReflect(this);
            }
        }
    }

    private static void installLeakCanaryByReflect(Application application) {
        Log.d("performance", "start invoke installLeakCanaryByReflect");

        try {
            // reflect invoke code
            // LeakCanary.install(this);
            Class leakCanaryClass = Class.forName("com.squareup.leakcanary.LeakCanary");
            Method methodInstall = leakCanaryClass.getMethod("install", Application.class);
            methodInstall.invoke(leakCanaryClass, application);
            Log.d("performance", "complete invoke installLeakCanaryByReflect");
        } catch (ClassNotFoundException e) {
            Log.d("performance", "installLeakCanaryByReflect ClassNotFoundException");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.d("performance", "installLeakCanaryByReflect NoSuchMethodException");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.d("performance", "installLeakCanaryByReflect InvocationTargetException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.d("performance", "installLeakCanaryByReflect IllegalAccessException");
            e.printStackTrace();
        }
    }

    private void printMemoryLoop() {
        MemoryUtil.printMemoryLoop();
    }

    private void startBlockCanaryInDebugMode() {
        // only available in debug mode
        // class found
        // invoke by reflect

        Log.d("performance", "startBlockCanaryInDebugMode DEBUG = " + DEBUG);
        if (DEBUG) {
            boolean classFound = false;
            try {
                Class blockCanaryClass = Class.forName("com.github.moduth.blockcanary.BlockCanary");
                if (blockCanaryClass != null) {
                    classFound = true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Log.d("performance", "BlockCanary classFound = " + classFound);
            if (classFound) {
                /*
                // normal code
                BlockCanary blockCanary = BlockCanary.install(this, new AppBlockCanaryContext());
                blockCanary.start();
                */

                // reflect invoke code
                startBlockCanaryByReflect(this.getApplicationContext());
            }

        }
    }

    private static void startBlockCanaryByReflect(Context applicationContext) {
        Log.d("performance", "start invoke startBlockCanaryByReflect");

        try {
            // reflect invoke code
            // BlockCanary blockCanary = BlockCanary.install(context, new AppBlockCanaryContext());
            Class blockCanaryClass = Class.forName("com.github.moduth.blockcanary.BlockCanary");
            Class blockCanaryContextClass = Class.forName("com.github.moduth.blockcanary.BlockCanaryContext");
            Method methodInstall = blockCanaryClass.getMethod("install", Context.class, blockCanaryContextClass);
            Class appBlockCanaryContextClass = Class.forName("com.example.debugPerformance.AppBlockCanaryContext");
            Object appBlockCanaryContextInstance = appBlockCanaryContextClass.newInstance();
            Object blockCanaryObject = methodInstall.invoke(blockCanaryClass, applicationContext, appBlockCanaryContextInstance);

            // blockCanary.start();
            Method methodStart = blockCanaryClass.getMethod("start");
            methodStart.invoke(blockCanaryObject);
            Log.d("performance", "complete invoke startBlockCanaryByReflect");
        } catch (ClassNotFoundException e) {
            Log.d("performance", "startBlockCanaryByReflect ClassNotFoundException");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.d("performance", "startBlockCanaryByReflect NoSuchMethodException");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.d("performance", "startBlockCanaryByReflect InvocationTargetException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.d("performance", "startBlockCanaryByReflect IllegalAccessException");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDebuggable(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        boolean isDebuggable = (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebuggable;
    }

}
