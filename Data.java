package teityan.com.appkn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tenko_w8othx0 on 2018/02/18.
 */

public class Data extends Application {


    public Data() {

    }

    public String Datef() {
        String data1;
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(nowDate);
        data1 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());

        return data1;
    }

    public String up() {
        final String data1=Datef();
        final String pkd[][]=pakege();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        final DatabaseReference refEmail = database.getReference().child(data1);
        final String hoge;
        final String result="成功";
        int count=0;
        hoge =pkd[count][1];
        final String aname=pkd[count][2];
        final  String times=pkd[count][3];
        refEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String aaa = (String) dataSnapshot.child(hoge).child("time").getValue();


                refMsg.child(hoge).child("time").setValue(String.valueOf(hoge));
                refMsg.child(hoge).child("name").setValue(String.valueOf(hoge));
                refMsg.child(hoge).child("name").setValue(String.valueOf(aname));
                refMsg.child(hoge).child("time").setValue(String.valueOf(times));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("seit", "ValueEventListener#onCancelled");
                result="失敗";
                // サーバーエラーかもしくはセキュリティとデータべーすルールによってデータにアクセスできない
            }
        });
        return result;
    }

    public String[][] pakege() {
        String pkh[][]={};
        final PackageManager pm = getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        int count = 0;
        for (PackageInfo info : pm.getInstalledPackages(flags)) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) continue;
            String pks = info.packageName;
            String pkn = info.applicationInfo.loadLabel(pm).toString();
            String data1 = Datef();
            SharedPreferences dataStore = getSharedPreferences(data1, MODE_PRIVATE);
            int times = dataStore.getInt(pks, 0);
            String time = times + "";
            String pks2 = pks.replace(".", "-");
            pkh[count] = new String[]{pks2, pkn, time};
            count++;
        }
   return pkh;
    }
}
