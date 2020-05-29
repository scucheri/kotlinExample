package com.example.bytedance.myapplication;

import android.util.Log;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "JavaCrash";
        String name;

        public MyUncaughtExceptionHandler() {
            this.name = Thread.currentThread().getName();
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Log.e(TAG, "In Handler " + name);
            Log.e(TAG, "Thread name " + t.getName());

            Log.e(TAG, String.valueOf(e.getStackTrace()));
            e.printStackTrace();
        }
    }