package org.jitsi.android;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;

import my.myusri.jitu.MainActivity;

/**
 * Simplified Jitsi Application global context
 */
public class JitsiApplication extends MultiDexApplication {
    public static final String TAG         = "Jujitsu";
    public static final String ACTION_EXIT = "org.jitsi.android.exit";

    public static JitsiApplication instance;
    private static Activity currentActivity;

    public static Context getGlobalContext() {
        return instance;
    }

    public static Class<?> getHomeScreenActivityClass() {
        return MainActivity.class;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        JitsiApplication.currentActivity = currentActivity;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    public static Intent getHomeIntent()
    {
        Intent homeIntent = new Intent(instance, getHomeScreenActivityClass());
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return homeIntent;
    }

    public static PendingIntent getJitsiIconIntent() {
        return PendingIntent.getActivity(
                getGlobalContext(), 0,
                getHomeIntent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static AudioManager getAudioManager() {
        return (AudioManager) getGlobalContext()
                .getSystemService(Context.AUDIO_SERVICE);
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

    public static void showSendLogsDialog() {
        // TODO
    }

    public static String getResString(int id)
    {
        return getAppResources().getString(id);
    }

    public static String getResString(int id, Object ... arg)
    {
        return getAppResources().getString(id, arg);
    }
}
