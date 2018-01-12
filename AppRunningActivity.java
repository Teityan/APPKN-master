package teityan.com.appkn;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppRunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_running);
        ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
// 現在稼働中のプロセスを取得
        List<ActivityManager.RunningAppProcessInfo> rapis= am.getRunningAppProcesses();
        PackageManager pm = getPackageManager();
        for(ActivityManager.RunningAppProcessInfo rapi : rapis) {
            try {
                // processNameはパッケージ名…と思う
                ApplicationInfo ai = pm.getApplicationInfo(rapi.processName, 0);
                // アプリ名を取得
                Log.d("rapi", "AppName = " + pm.getApplicationLabel(ai));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
