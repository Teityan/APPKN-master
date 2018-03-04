package teityan.com.appkn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking2Activity extends AppCompatActivity {
    final List<Getdata> lists = new ArrayList<>();
    final List<Map<String, String>> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking2);
        Data c=new Data();
        final String data1 = c.getDate();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        final DatabaseReference refEmail = database.getReference().child(data1);
        final String[] hoges = new String[1];
        String fff;




        refMsg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int count=1;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){


                    String appname = (String) dataSnapshot.child("name").getValue();
                    String time = (String) dataSnapshot.child("time").getValue();
                    count++;
                    Map<String, String> item = new HashMap();
                    item.put("Subject", String.valueOf(appname));
                    item.put("Comment", String.valueOf(time));
                    data.add(item);
                    Log.d(appname,time);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ログを記録するなどError時の処理を記載
            }
        });




        // ListViewに表示するリスト項目をArrayListで準備する
        for(Getdata city : lists) {
            Map<String, String> item = new HashMap();


            item.put("Subject", String.valueOf(city.gappname));
            item.put("Comment", String.valueOf(city.gtime));
            data.add(item);
            Log.d(city.gappname,city.gtime);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] { "Subject", "Comment" },
                new int[] { android.R.id.text1, android.R.id.text2});

        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.ListView);
        listView.setAdapter(adapter);
    }
    class Getdata {
        String gtime;
        String gappname;
        Getdata( String gtime, String gappname) {
            this.gtime = gtime;
            this.gappname = gappname;
        }
    }
}
