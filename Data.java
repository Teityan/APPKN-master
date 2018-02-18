package teityan.com.appkn;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tenko_w8othx0 on 2018/02/18.
 */

public class Data {


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

    public String up(Context context) {
        final String data1 = Datef();
        final List<CellData> list = pakege(context);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        final DatabaseReference refEmail = database.getReference().child(data1);
        final String[] hoges = new String[1];
        String fff ;

        refEmail.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(CellData city : list) {
                    String hoge=city.pname;
                    String aaa = (String) dataSnapshot.child(hoge).child("time").getValue();
                    final int time;
                    final int times=city.time;
                    final int time2;
                    final String aname=city.appname;
                    if(aaa==null) {
                        time2=0;
                        Log.d(hoge,"dddsdsd");

                    }else{
                        time2 = Integer.parseInt(aaa);
                        Log.d(hoge,aaa);

                    }
                    time = times + time2;
                    refMsg.child(hoge).child("time").setValue(String.valueOf(hoge));
                    refMsg.child(hoge).child("name").setValue(String.valueOf(hoge));
                    refMsg.child(hoge).child("name").setValue(String.valueOf(aname));
                    refMsg.child(hoge).child("time").setValue(String.valueOf(time));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("seit", "ValueEventListener#onCancelled");
                // サーバーエラーかもしくはセキュリティとデータべーすルールによってデータにアクセスできない
            }
        });


        final String result = "成功";
        /*int count=0;
        hoge =pkd[count][1];
        final String aname=pkd[count][2];
        final String times =pkd[count][3];
addListenerForSingleValueEvent()
        refEmail.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String aaa = (String) dataSnapshot.child(hoge).child("time").getValue();
                final  int time;
                final  int time2;
                time2=Integer.parseInt(aaa);
                time = Integer.valueOf(times)+time2;
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
        */
        return result;
    }

    public List<CellData> pakege(Context context) {
        List<CellData> list = new ArrayList<>();
        final PackageManager pm = context.getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        int count = 0;
        for (PackageInfo info : pm.getInstalledPackages(flags)) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)continue;
            String pks = info.packageName;
            String pkn = info.applicationInfo.loadLabel(pm).toString();
            String data1 = Datef();
            SharedPreferences dataStore = context.getSharedPreferences(data1, MODE_PRIVATE);
            int times = dataStore.getInt(pks, 6);
            String pks2 = pks.replace(".", "-");
            CellData data = new CellData(pks2, times, pkn);
            list.add(data);
            count++;
        }
        return list;
    }

    class CellData {
        String pname;
        int time;
        String appname;

        CellData(String pname, int time, String appname) {
            this.pname = pname;
            this.time = time;
            this.appname = appname;
        }
    }
}
