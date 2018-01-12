package teityan.com.appkn;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    //メニュー








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle( "スマホが与える体への影響" );

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        WebView myWebView = new WebView(this);
        setContentView(myWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        //標準ブラウザをキャンセル
        myWebView.setWebViewClient(new ViewClient());
        //アプリ起動時に読み込むURL
        android.net.ConnectivityManager cm = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            myWebView.loadUrl("https://teichat.tk/login/Google/");
        } else {
            Toast.makeText(this, "インターネットに接続されていません", Toast.LENGTH_LONG).show();
            Log.d("DEBUG", "接続されていません");
            myWebView.loadUrl("file:///android_asset/4.html");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();
        if(itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public final class ViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view , String url){
            //ロード完了時にやりたい事を書く
            progressDialog.dismiss();
        }
    }

    }
