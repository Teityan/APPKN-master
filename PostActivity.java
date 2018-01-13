package teityan.com.appkn;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg;
    String pname;
    String pk;
    String data1;
    int time;
    Query query;
    FirebaseFirestore db=FirebaseFirestore.getInstance();;
    // 端末にインストール済のアプリケーション一覧情報を取得


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        final PackageManager pm = getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        final String[] title = new String[1];
        data1 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
        refMsg = database.getReference(data1);
        for (PackageInfo info : pm.getInstalledPackages(flags)) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)
                continue;
            pk = info.packageName;
            SharedPreferences dataStore = getSharedPreferences(data1, MODE_PRIVATE);

            time = dataStore.getInt(pk, 0);
            final String times = String.valueOf(time);
            if(times=="0")continue;;
            final String str2 = pk.replace(".", "-");
            //refMsg.child(str2+).child("UserId").setValue(String.valueOf(time));
            refMsg.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        title[0] = (String) dataSnapshot.child(String.valueOf("time")).getValue();
                        Log.d("test",title[0]);

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ログを記録するなどError時の処理を記載
                }
            });
            Toast.makeText(this,title[0],Toast.LENGTH_SHORT).show();
            refMsg.child(str2).child("time").setValue(String.valueOf(str2));
            refMsg.child(str2).child("time").setValue(String.valueOf(time));

            Log.d("aaaa", pk + ":時間：" + time);

        }
    }


    public void post(View v) {
        String aaa="teityan-com-appkn";
        SolutionCounters solutionCounters=new SolutionCounters();
        DocumentReference newCityRef = db.collection("cities").document("aaaa");
        solutionCounters.createCounter(newCityRef,10);
       // solutionCounters.incrementCounter(newCityRef,1);

    }
}
