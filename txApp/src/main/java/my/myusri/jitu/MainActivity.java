package my.myusri.jitu;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import org.jitsi.R;
import org.jitsi.impl.neomedia.MediaUtils;
import org.jitsi.impl.neomedia.codec.video.AndroidDecoder;
import org.jitsi.impl.neomedia.device.util.CameraUtils;
import org.jitsi.impl.neomedia.device.util.OpenGlCtxProvider;
import org.jitsi.impl.neomedia.device.util.PreviewSurfaceProvider;
import org.jitsi.service.libjitsi.*;
import org.jitsi.service.neomedia.MediaType;
import org.jitsi.service.neomedia.format.MediaFormat;

import java.util.Set;
import java.util.TreeSet;

import static my.myusri.jitu.JituApplication.showToast;

public class MainActivity extends AppCompatActivity
  implements CompoundButton.OnCheckedChangeListener {

  private final static String TAG = "MainActivity";
  private static final int PERMS_REQ = 1000;

  ToggleButton tglStart;
  AutoCompleteTextView txtAddress;

  private ArrayAdapter<String> addressList;
  private SharedPreferences prefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    prefs = getPreferences(MODE_PRIVATE);

    tglStart = findViewById(R.id.toggleStart);
    tglStart.setOnCheckedChangeListener(this);

    addressList = new ArrayAdapter<>(this, android.R.layout.select_dialog_item);
    txtAddress = findViewById(R.id.addressText);
    txtAddress.setThreshold(1);
    txtAddress.setAdapter(addressList);

    String path = getApplicationContext().getFilesDir().getPath();
    System.setProperty(
      "net.java.sip.communicator.CONFIGURATION_FILE_NAME", "jitsi.properties");
    System.setProperty(
      "net.java.sip.communicator.SC_HOME_DIR_NAME", ".jitsi");
    System.setProperty(
      "net.java.sip.communicator.SC_HOME_DIR_LOCATION", path);

    // Set up remote view

    ViewGroup container = findViewById(R.id.remoteView);
    AndroidDecoder.renderSurfaceProvider
            = new PreviewSurfaceProvider(
            this, container, false);


    // Set up the video preview surface for the camera source

    container = findViewById(R.id.localView);
    PreviewSurfaceProvider surface = new PreviewSurfaceProvider(
      this, container, true);
    CameraUtils.setPreviewSurfaceProvider(surface);

    // Now setup OpenGL context

    CameraUtils.localPreviewCtxProvider
      = new OpenGlCtxProvider(this, container);

    LibJitsi.start();

    MediaFormat[] formats = MediaUtils.getMediaFormats(MediaType.AUDIO);
    Log.d(TAG, "Audio formats:");
    for (MediaFormat f : formats) {
      Log.d(TAG, f.toString());
    }
    formats = MediaUtils.getMediaFormats(MediaType.VIDEO);
    Log.d(TAG, "Video formats:");
    for (MediaFormat f : formats) {
      Log.d(TAG, f.toString());
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    Set<String> addresses = prefs.getStringSet("addresses", null);
    if (addresses != null) {
      addressList.clear();
      for (String address: addresses)
        addressList.add(address);
    }
  }

  @Override
  protected void onStop() {
    TreeSet<String> addresses = new TreeSet<>();
    for (int i = 0; i < addressList.getCount(); ++i)
      addresses.add(addressList.getItem(i));
    SharedPreferences.Editor ed = prefs.edit();
    ed.putStringSet("addresses", addresses);
    ed.apply();

    super.onStop();
  }

  @Override
  protected void onDestroy() {
    LibJitsi.stop();
    super.onDestroy();
  }

  @Override
  public void onCheckedChanged(CompoundButton v, boolean b) {
    try {
      switch (v.getId()) {
        case R.id.toggleStart:
          Log.i(TAG, String.format("Start: %b", b));
          if (b) startTransmit();
          else stopTransmit();
          break;
        default:
      }
    } catch (Exception ex) {
      Log.e(TAG, "Error occurred", ex);
    }
  }

  private void doStartTransmit() throws Exception {
    String address = txtAddress.getText().toString().trim();
    if (address.length() == 0) {
      showToast("Please provide address");
      return;
    }
    else {
      addressList.remove(address);
      addressList.add(address);
    }

    Log.d(TAG, "start transmitting");
    Log.d(TAG, "call address:" + address);
    JituService.startTransmit(this, 7100, address, 7100);
  }

  private void startTransmit() throws Exception {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      int hasCameraPerm = checkSelfPermission(Manifest.permission.CAMERA);
      int hasMicPerm = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
      int hasWritePerm = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
      if (hasCameraPerm != PackageManager.PERMISSION_GRANTED
        || hasMicPerm != PackageManager.PERMISSION_GRANTED
        || hasWritePerm != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
          Manifest.permission.CAMERA,
          Manifest.permission.RECORD_AUDIO,
          Manifest.permission.WRITE_EXTERNAL_STORAGE
          },
          PERMS_REQ);
        return;
      }
    }
    doStartTransmit();
  }

  private void stopTransmit() {
    Log.d(TAG, "stop transmitting");
    JituService.stopTransmit(this);
  }

  @Override
  public void onRequestPermissionsResult(
    int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == PERMS_REQ) {
      int hasCameraPerm = grantResults[0];
      int hasMicPerm = grantResults[1];
      int hasWritePerm = grantResults[2];
      if (hasCameraPerm == PackageManager.PERMISSION_GRANTED
              && hasMicPerm == PackageManager.PERMISSION_GRANTED
              && hasWritePerm == PackageManager.PERMISSION_GRANTED)
        try {
          doStartTransmit();
        } catch (Exception e) {
          Log.e(TAG, "Problem transmitting", e);
        }
    }
  }
}
