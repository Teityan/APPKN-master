package teityan.com.appkn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Record3Activity extends AppCompatActivity {
    ListView listView;
    final ArrayList<String> hoge = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record3);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle( "ランキング" );
        listView = (ListView) findViewById(R.id.ListView);
        Data c = new Data();
        final String data1 = c.Datef();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refMsg = database.getReference(data1);
        refMsg.keepSynced(false);
        final DatabaseReference refEmail = database.getReference().child(data1);
        hoge.add(data1+"のランキングです！");


        refMsg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int count = 1;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    String appname = (String) dataSnapshot.child("name").getValue();
                    String time = (String) dataSnapshot.child("time").getValue();
                    hoge.add(count + "位" + appname + ":" + time + "分");
                    Log.d(appname, time);
                    count++;
                }
                set();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ログを記録するなどError時の処理を記載
            }

        });


    }

    public void set () {
        ListView listView = new ListView(this);
        setContentView(listView);

        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hoge);

        listView.setAdapter(arrayAdapter);

    }
}
