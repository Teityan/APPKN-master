package teityan.com.appkn;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Record3Activity extends AppCompatActivity {
    ListView listView;
    final ArrayList<String> hoge = new ArrayList();
    final Map<Integer, String> mMap = new HashMap();
    Data dataClass = new Data();
    final String date = dataClass.getDate();
    ArrayList<App> appList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record3);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle( "ランキング" );
        listView = (ListView) findViewById(R.id.ListView);

        hoge.add(date+"のランキングです！");
       // appList=dataClass.getAppList();
        dataClass.getAppList();




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();
        if(itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void set (Context context) {
        ListView listView = new ListView(context);
        setContentView(listView);

        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hoge);

        listView.setAdapter(arrayAdapter);

    }
public void callBack(ArrayList<App> returns){
        appList=returns;
    for (App app :appList){
        Log.d(app.getSyainName(),"時間"+app.getSyainNo());
    }


    //SyainComparatorクラスの条件に従いソートする
      Collections.sort(appList, new Sorts());

    //結果を画面表示する
    Iterator<App> it = appList.iterator();
    int c=0;
    while (it.hasNext()) {
        App data = it.next();
        Log.d(data.getSyainName(),"uhihohh"+data.getSyainNo());
        hoge.add(c+"位"+data.getSyainName()+data.getSyainNo()+"分");
    c++;
    }
    set(getApplicationContext());
}
}
