package teityan.com.appkn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoActivity extends AppCompatActivity {
    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle("このアプリについて");
        textView=(TextView)findViewById(R.id.textView16) ;
        textView1=(TextView)findViewById(R.id.textView18);
        //PackageInfoの取得
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //パッケージのバージョンネームの取得
        String versionName    = packageInfo.versionName;
        //最後にインストールした日付
        String lastUpdateTime   = String.valueOf(packageInfo.lastUpdateTime);

        textView.setText(versionName);
        textView1.setText(lastUpdateTime);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
