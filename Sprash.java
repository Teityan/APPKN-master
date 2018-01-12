package teityan.com.appkn;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Sprash extends AppCompatActivity {
    private final static int SPLASH_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Sprash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }

    /**
     * バックキー無効。
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
