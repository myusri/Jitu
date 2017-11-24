package org.jitsi.android;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;

/**
 * Simplified Jitsi Application global context
 */
public class JitsiApplication extends MultiDexApplication {
    public static JitsiApplication instance;

    public static Context getGlobalContext() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        instance = null;
        super.onTerminate();
    }
}
