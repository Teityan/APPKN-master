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

public class LoginActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private static final int RC_ACHIEVEMENT_UI = 9003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 初期設定
        // 各種リスナー登録とGoogleAPIで利用するAPIやスコープの設定
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
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

        // プレイヤー情報取得
        Player p = Games.Players.getCurrentPlayer(mGoogleApiClient);

        // 試しにプレイヤー名を表示
        String displayName = "???";
        if (p != null) {
            displayName = p.getDisplayName();
        }
        Toast.makeText(this, String.format("%s でログインしています。", displayName), Toast.LENGTH_SHORT).show();
        Games.Achievements.unlock(mGoogleApiClient, "CgkIrPeUp7UVEAIQAQ");


    }
    public void  Ac(View v){
        startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient),RC_ACHIEVEMENT_UI);

    }
    public void out(View v){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            //this.mGoogleApiClient.signOut();
            Toast.makeText(this, String.format("ログアウトしました。"), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, String.format("ログインしていません。"), Toast.LENGTH_SHORT).show();
        }
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
