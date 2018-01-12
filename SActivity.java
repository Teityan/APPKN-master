package teityan.com.appkn;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.R.id.list;
import static android.R.id.mask;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SActivity extends AppCompatActivity {
    static final int REQUEST_CODE = 1;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("初期設定");
        setContentView(R.layout.activity_s);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){
            Log.d("eded", "eee");


            // permissionが許可されていません
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 許可ダイアログで今後表示しないにチェックされていない場合
                Log.d("qwdqw", "dwd");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }

            // permissionを許可してほしい理由の表示など

            // 許可ダイアログの表示
            // MY_PERMISSIONS_REQUEST_READ_CONTACTSはアプリ内で独自定義したrequestCodeの値

            Log.d("ededwdwd", "eeewdwd");
        }

        viewPager.setAdapter(
                new MyFragmentStatePagerAdapter(getSupportFragmentManager()));

        if (isUsageStatsAllowed()) {

        }else {
            startActivity(new Intent("android.settings.USAGE_ACCESS_SETTINGS"));
        }


}
    public boolean isUsageStatsAllowed() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int uid = android.os.Process.myUid();
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, uid, getPackageName());
        return  mode == AppOpsManager.MODE_ALLOWED;
    }
}