package teityan.com.appkn;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class StopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        // 戻るボタンが押されたとき
        if (e.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // ボタンが押されたとき
            finishAndRemoveTask();

        }
        return false;
    }
}