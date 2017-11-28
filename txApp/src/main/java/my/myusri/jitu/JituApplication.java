package my.myusri.jitu;

import android.content.Context;
import android.widget.Toast;

import org.jitsi.android.JitsiApplication;

public class JituApplication extends JitsiApplication {

  private static Toast toast;

  public static void showToast(String msg) {
    Context ctx = getGlobalContext();
    if (toast == null) toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
    else toast.setText(msg);
    toast.show();
  }
}
