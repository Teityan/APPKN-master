package teityan.com.appkn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by tenko_w8othx0 on 2017/12/11.
 */

public class BootReceiver extends BroadcastReceiver {
@Override
public void onReceive(Context context, Intent intent) {
         // TODO Auto-generated method stub
    if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
        // サービスの起動
         Intent service = new Intent(context, ServiceTimer.class);
         context.startService(service);
    }
  }
}