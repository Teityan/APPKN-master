package teityan.com.appkn;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    SharedPreferences dataStore;
    CharSequence dateText;
    int Data2;
    TextView textView1;
    int Data3;
    private ProgressDialog progressDialog;
    TextView textViewm;
    View view;
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences dataStores = getSharedPreferences("Goal", MODE_PRIVATE);
        //初回起動判定
        Log.d("test", "test");
        String Traffic= String.valueOf(TrafficStats.getMobileTxBytes());
        Toast.makeText(this, String.format(Traffic), Toast.LENGTH_SHORT).show();

            dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
            Data3 = dataStore.getInt("start", 0);
            if (Data3 == 0) {
                Log.d("AppLaunchChecker", "はじめてアプリを起動した");
                Intent intent = new Intent(MainActivity.this, SActivity.class);
                startActivity(intent);
                AppLaunchChecker.onActivityCreate(this);
                SharedPreferences.Editor editor = dataStore.edit();
                editor.putInt("start", 1);
                editor.apply();
                Log.d("test", "test");
            }





        AppLaunchChecker.onActivityCreate(this);
        //日にちの取得
        textViewm = (TextView) findViewById(R.id.textView);

        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        String strDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String strPreviousDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());

        //ツールバーの設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("トップ");
        AppLaunchChecker.onActivityCreate(this);


        //使用時間の表示
        dateText = android.text.format.DateFormat.format("yyyy/MM/dd ", Calendar.getInstance());
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        Data2 = dataStore.getInt(strDate, 0);
        String s = dataStores.getString("input", "0");
        int Goal = Integer.parseInt(s);
        Data3 = dataStore.getInt(strPreviousDate, 0);
        textView1 = (TextView) findViewById(R.id.textView7);
        TextView textGoal = (TextView) findViewById(R.id.textView22);
        int Goals = Data2 - Goal;
        textGoal.setText("目標との差" + Goals + "分");
        textView1.setText(Data2 + "分");
        //textViewm.setText(Data2+"分");


        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        //ドロワーメニュー

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView t = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
        t.setText(Data2 + "分");
        //グラフの作成
        chart = (BarChart) findViewById(R.id.chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "使用時間");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);


    }

    public void AddValuesToBARENTRY() {

        BARENTRY.add(new BarEntry(Data2, 0));
        BARENTRY.add(new BarEntry(Data3, 1));
    }


    public void AddValuesToBarEntryLabels() {

        BarEntryLabels.add("今日");
        BarEntryLabels.add("昨日");
        //BarEntryLabels.add("一昨日");

    }
    //グラフ終了・ドロワーメニュースタート

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    //ドロワーメニューの動作設定
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {
            // トップ
            Intent intent = new Intent(MainActivity.this,PostActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            //
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, SActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "スマホ使い過ぎじゃん？");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "今日" + Data2 + "分使ったよもうちょっと減らさなきゃ！" +
                    "みんなも減らしてみないかい？");
            startActivity(shareIntent);


        } else if (id == R.id.nav_send) {


            Intent intent = new Intent(getApplication(), PerformanceActivity.class);
            intent.putExtra("DATA1", 1);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}