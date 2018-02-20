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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking2);
        Data c=new Data();
        final String data1 = c.Datef();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        final DatabaseReference refEmail = database.getReference().child(data1);
        final String[] hoges = new String[1];
        String fff;
        final List<Getdata> lists = new ArrayList<>();
        final List<Map<String, String>> data = new ArrayList<Map<String, String>>();


        refMsg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    String appname = (String) dataSnapshot.child("name").getValue();
                    String time = (String) dataSnapshot.child("time").getValue();
                    Map<String, String> item = new HashMap<String, String>();
                    item.put("name", appname);
                    item.put("time", time);
                    data.add(item);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ログを記録するなどError時の処理を記載
            }
        });
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{ "name", "time" },
                new int[] { android.R.id.text1, android.R.id.text2});

        // ListViewにArrayAdapterを設定する
        ListView listView = findViewById(R.id.ListView);
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
