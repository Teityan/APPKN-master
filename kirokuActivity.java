package teityan.com.appkn;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static teityan.com.appkn.R.id.textView;

public class kirokuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    SharedPreferences dataStore;
    int TimeCount;
    Date date1;
    CharSequence dateText;
    Date newFragment;
    TextView textView;
    float bbb;
    String TimeC;
    Calendar cal;
    int Data2;
    int Data3;
    Date nowDate;
    TextView textview1;;
    LayoutInflater inflater;
    ViewGroup container;





    @Override
    protected void onCreate(Bundle savedInstanceState )  {
        setContentView(R.layout.activity_kiroku);
        View view =  inflater.inflate(R.layout.activity_kiroku, null);
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        TimeCount=dataStore.getInt("inputs", 0);
        bbb=(float)TimeCount;
        date1= new Date();
        textView=(TextView)view.findViewById(R.id.textView6);
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        String strDate = new SimpleDateFormat("yyyyMMdd",Locale.JAPAN).format(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String strPreviousDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
        textview1 = (TextView)view.findViewById(R.id.Date);



        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.aaa);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle( "今までの記録" );
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        Data2 = dataStore.getInt(strDate, 0);
        Data3 = dataStore.getInt(strPreviousDate, 0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        if(TimeCount>60) {

        }else{

        }

        TimeC=Data2+"分";
        String dateText1=(String)dateText;
        String Timea= strDate+TimeCount+"分";

        // 要素の追加（1）
        adapter.add(Timea);
        //adapter.add("昨日"+TimeCount1);
       // adapter.add("一昨日"+TimeCount2);

        listView.setAdapter(adapter);
        chart = (BarChart) findViewById(R.id.chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "Projects");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);



    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(Data2, 0));
        BARENTRY.add(new BarEntry(Data3, 1));

    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("今日");
        BarEntryLabels.add("昨日");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();
        if(itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String DatePick=null;
        DatePick=String.valueOf(year)+ String.valueOf(monthOfYear + 1)+ String.valueOf(dayOfMonth);

        //textview1.setText(DatePick);
        textview1.setText("aaaa");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePick();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }





}
