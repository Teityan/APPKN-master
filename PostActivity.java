package teityan.com.appkn;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PostActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg;
    String pname;
    String pk;
    String data1;
    int time;
    Query query;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String aname;
    String str2;
    int gtime;
    private DatabaseReference mDatabase;
    // 端末にインストール済のアプリケーション一覧情報を取得


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
 /*
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog/posts");
        final PackageManager pm = getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        final String[] title = new String[1];
        data1 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
        refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        for (PackageInfo info : pm.getInstalledPackages(flags)) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)
                continue;
            pk = info.packageName;
            aname = info.applicationInfo.loadLabel(pm).toString();
            Log.d("sssss", aname);
            SharedPreferences dataStore = getSharedPreferences(data1, MODE_PRIVATE);

            time = dataStore.getInt(pk, 0);
            time = 6;
            // if (time == 0) continue;
            str2 = pk.replace(".", "-");
            //refMsg.child(str2+).child("UserId").setValue(String.valueOf(time));
            String sss = str2;
            String pks[];



            DatabaseReference refEmail = database.getReference().child(data1);

            refEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String hoge = str2;


                    String aaa = (String) dataSnapshot.child(hoge).child("time").getValue();


                    if (aaa == null) {
                        Log.d("hogehoge", pk);
                        return;
                    } else {
                        gtime = Integer.parseInt(aaa);
                    }
                    Log.d(gtime + "jj", aaa);
                    int times = gtime + time;
                    Log.d(pk, gtime + "");

                    refMsg.child(hoge).child("time").setValue(String.valueOf(hoge));
                    refMsg.child(hoge).child("name").setValue(String.valueOf(hoge));
                    refMsg.child(hoge).child("name").setValue(String.valueOf(aname));
                    refMsg.child(hoge).child("time").setValue(String.valueOf(times));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("seit", "ValueEventListener#onCancelled");
                    // サーバーエラーかもしくはセキュリティとデータべーすルールによってデータにアクセスできない
                }
            });


             //アプリ情報取得


            Log.d("aaaa", pk + ":時間：" + time);

        }
*/
    }


    public void post(View v) {
        Data c;
        c = new Data();
        Context context=this;
    String re=c.up(context);
        Toast.makeText(this,re+"でした",Toast.LENGTH_SHORT).show();
    }
}
