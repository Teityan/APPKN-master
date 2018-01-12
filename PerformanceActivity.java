package teityan.com.appkn;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;

public class PerformanceActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private static final int RC_ACHIEVEMENT_UI = 9003;
    int data1;
    String Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        // 初期設定
        // 各種リスナー登録とGoogleAPIで利用するAPIやスコープの設定
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
        Intent intent = getIntent();
        data1 = intent.getIntExtra("DATA1", 0);
        if(data1==0){
            Toast.makeText(this, String.format("実績解除に失敗しました。"), Toast.LENGTH_SHORT).show();
            finish();
        }else if(data1==1) {
        Code="CgkIrPeUp7UVEAIQAg";
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 今回は画面が表示されるたびに必ず接続させる
        mGoogleApiClient.connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            mIntentInProgress = false;
            if (resultCode != RESULT_OK) {
                // エラーの場合、resultCodeにGamesActivityResultCodes内の値が入っている
                return;
            }
            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.reconnect();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {


        if(Code==""){
            Toast.makeText(this, String.format("実績解除に失敗しました。"), Toast.LENGTH_SHORT).show();
            finish();
        }else if(Code==null){
            Toast.makeText(this, String.format("実績解除に失敗しました。"), Toast.LENGTH_SHORT).show();
            finish();
        }
        Games.Achievements.unlock(mGoogleApiClient, Code);
        Toast.makeText(this, String.format("実績解除に成功しました。"), Toast.LENGTH_SHORT).show();
        finish();

    }


    @Override
    public void onConnectionSuspended(int i) {
        // mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        int errorCode = connectionResult.getErrorCode();

        // サインインしていない場合、サインイン処理を実行する
        if (errorCode == ConnectionResult.SIGN_IN_REQUIRED
                && !mIntentInProgress && connectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                connectionResult.startResolutionForResult(this, 100);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }



    }
}
