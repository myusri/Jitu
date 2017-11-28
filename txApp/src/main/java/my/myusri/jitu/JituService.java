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

import org.jitsi.service.libjitsi.LibJitsi;
import org.jitsi.service.neomedia.DefaultStreamConnector;
import org.jitsi.service.neomedia.MediaDirection;
import org.jitsi.service.neomedia.MediaService;
import org.jitsi.service.neomedia.MediaStream;
import org.jitsi.service.neomedia.MediaStreamTarget;
import org.jitsi.service.neomedia.MediaType;
import org.jitsi.service.neomedia.MediaUseCase;
import org.jitsi.service.neomedia.StreamConnector;
import org.jitsi.service.neomedia.device.MediaDevice;
import org.jitsi.service.neomedia.format.MediaFormat;
import org.jitsi.service.neomedia.format.MediaFormatFactory;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import static my.myusri.jitu.JituApplication.showToast;

public class JituService extends Service {
  private static final String TAG = "JituService";
  private static final String JITU_SERVICE = "my.myusri.jitu.action.JITU_SERVICE";
  private static final int START_TRANSMIT = 1000;
  private static final int STOP_TRANSMIT = 1001;

  private final class ServiceHandler extends Handler {

    private MediaStream audioStream;
    private MediaStream videoStream;

    ServiceHandler(Looper looper) { super(looper); }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
          case START_TRANSMIT:
            Bundle params = msg.getData();
            int local = params.getInt("localPortStart", 7100);
            int remote = params.getInt("remotePortStart", 7100);
            String host = params.getString("remoteHost");
            try {
              startTransmit(local, host, remote);
            } catch (IOException e) {
              Log.e(TAG, "problem transmitting", e);
              postToast(e.getMessage());
            }
            break;
          case STOP_TRANSMIT:
            stopTransmit();
            break;
        }
    }

    private void startTransmit(int localPort, String remoteHost, int remotePort)
      throws IOException {

      if (audioStream != null) {
        Log.w(TAG, "already started transmitting!");
        return;
      }
      InetAddress remote = InetAddress.getByName(remoteHost);
      MediaService mediaService = LibJitsi.getMediaService();
      MediaFormatFactory F = mediaService.getFormatFactory();
      MediaDevice dev = mediaService.getDefaultDevice(MediaType.AUDIO, MediaUseCase.CALL);
      audioStream = mediaService.createMediaStream(dev);
      audioStream.setDirection(MediaDirection.SENDONLY);
      MediaFormat fmt = F.createMediaFormat("G722", 8000, 1);
      audioStream.setFormat(fmt);
      StreamConnector con = new DefaultStreamConnector(
        new DatagramSocket(localPort),  // RTP
        new DatagramSocket(localPort+1)); // RTCP
      audioStream.setConnector(con);
      MediaStreamTarget target = new MediaStreamTarget(
        new InetSocketAddress(remote, remotePort), // RTP
        new InetSocketAddress(remote, remotePort+1)); // RTCP
      audioStream.setTarget(target);
      audioStream.setName("AUDIO");

      dev = mediaService.getDefaultDevice(MediaType.VIDEO, MediaUseCase.CALL);
      videoStream = mediaService.createMediaStream(dev);
      videoStream.setDirection(MediaDirection.SENDONLY);
      fmt = F.createMediaFormat("VP8");
      videoStream.addDynamicRTPPayloadType((byte)99, fmt);
      videoStream.setFormat(fmt);
      con = new DefaultStreamConnector(
        new DatagramSocket(localPort+2),  // RTP
        new DatagramSocket(localPort+3)); // RTCP
      videoStream.setConnector(con);
      target = new MediaStreamTarget(
        new InetSocketAddress(remote, remotePort+2), // RTP
        new InetSocketAddress(remote, remotePort+3)); // RTCP
      videoStream.setTarget(target);
      videoStream.setName("VIDEO");

      audioStream.start();
      videoStream.start();
    }

    private void stopTransmit() {
      if (audioStream != null) {
        try { audioStream.stop(); }
        finally {
          audioStream.close();
          audioStream = null;
        }
      }
      if (videoStream != null) {
        try { videoStream.stop(); }
        finally {
          videoStream.close();
          videoStream = null;
        }
      }
    }
  }

  private ServiceHandler serviceHandler;
  private Handler mainHandler;

  public JituService() {
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

    // also get the main UI thread handler
    mainHandler = new Handler(Looper.getMainLooper());
  }

  private void postToast(final String msg) {
    mainHandler.post(new Runnable() {
      @Override
      public void run() {
       showToast(msg);
      }
    });
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
