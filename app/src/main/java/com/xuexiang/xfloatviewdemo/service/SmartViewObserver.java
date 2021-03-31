package com.xuexiang.xfloatviewdemo.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class SmartViewObserver implements LifecycleEventObserver {
    private static String TAG = "SmartViewObserver";


    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event){
            case ON_CREATE:
                Log.d(TAG,"initVideo");
                break;
            case ON_START:
                Log.d(TAG,"startPlay");
                break;
            case ON_RESUME:
                Log.d(TAG,"resumePlay");
                break;
            default:
                break;
        }
    }
}
