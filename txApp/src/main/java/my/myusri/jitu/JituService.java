package my.myusri.jitu;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import org.jitsi.examples.AVTransmit2;

public class JituService extends Service {
  private static final String TAG = "JituService";
  private static final String JITU_SERVICE = "my.myusri.jitu.action.JITU_SERVICE";
  private static final int START_TRANSMIT = 1000;
  private static final int STOP_TRANSMIT = 1001;

  private final class ServiceHandler extends Handler {
    private AVTransmit2 transmit;

    ServiceHandler(Looper looper) { super(looper); }

    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case START_TRANSMIT:
          Bundle params = msg.getData();
          int local = params.getInt("localPortStart", 7100);
          int remote = params.getInt("remotePortStart", 7100);
          String host = params.getString("host");
          startTransmit(local, host, remote);
          break;
        case STOP_TRANSMIT:
          stopTransmit();
          break;
      }
    }

    private void startTransmit(int localPortStart, String remoteHost, int remotePortStart) {
      if (transmit != null) {
        Log.w(TAG, "already started transmitting!");
        return;
      }
      try {
        transmit = new AVTransmit2(
          Integer.toString(localPortStart), remoteHost, Integer.toString(remotePortStart));
        transmit.start();
      } catch (Exception e) {
        Log.e(TAG, "problem transmitting", e);
      }
    }

    private void stopTransmit() {
      if (transmit != null) transmit.stop();
      transmit = null;
      stopSelf();
    }
  }

  private ServiceHandler serviceHandler;

  public JituService() {
  }

  public static void startTransmit(
    Context context, int localPortStart,
    String remoteHost, int remotePortStart) {
    Intent intent = new Intent(context, JituService.class);
    intent.setAction(JITU_SERVICE);
    intent.putExtra("remoteHost", remoteHost);
    intent.putExtra("localPortStart", localPortStart);
    intent.putExtra("remotePortStart", remotePortStart);
    context.startService(intent);
  }

  public static void stopTransmit(Context context) {
    Intent intent = new Intent(context, JituService.class);
    intent.setAction(JITU_SERVICE);
    context.stopService(intent);
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    Log.d(TAG, "starting JituService thread");
    HandlerThread thread = new HandlerThread(
      "JituService", Process.THREAD_PRIORITY_BACKGROUND);
    thread.start();
    Looper serviceLooper = thread.getLooper();
    serviceHandler = new ServiceHandler(serviceLooper);
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent != null) {
      final String action = intent.getAction();
      if (JITU_SERVICE.equals(action)) {
        Message msg = serviceHandler.obtainMessage(START_TRANSMIT);
        Bundle params = new Bundle();
        params.putString("remoteHost", intent.getStringExtra("remoteHost"));
        params.putInt("localPortStart",
          intent.getIntExtra("localPortStart", 7100));
        params.putInt("remotePortStart",
          intent.getIntExtra("remotePortStart", 7100));
        msg.setData(params);
        serviceHandler.sendMessage(msg);
      }
    }
    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    Message msg = serviceHandler.obtainMessage(STOP_TRANSMIT);
    serviceHandler.sendMessage(msg);
    super.onDestroy();
  }
}
