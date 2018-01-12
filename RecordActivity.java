package teityan.com.appkn;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class RecordActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    private TextView textView;
    String Date;
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    String Date2;
    SharedPreferences dataStore;
    String Date5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.Date);

        //aaaaaa

        setTitle("このアプリについて");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        int monthOfYears=monthOfYear+1;
        //グラフの作成
        chart = (BarChart) findViewById(R.id.chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();


        Date = ""+year + monthOfYears + dayOfMonth;
        Date5 =""+year+monthOfYears+dayOfMonth;
        int Data3;
        AddValuesToBarEntryLabels();
        AddValuesToBARENTRY();
        int valLen = String.valueOf(dayOfMonth).length();
        int valLens = String.valueOf(monthOfYear).length();
        if (valLen==1){
            Date=""+year + monthOfYears +"0"+ dayOfMonth;
            if (valLens==1){
                Date=""+year + "0"+monthOfYears +"0"+ dayOfMonth;
            }
        }
        if (valLens==1){
            Date=""+year + "0"+monthOfYears + dayOfMonth;
            if (valLen==1){
                Date=""+year +"0"+ monthOfYears +"0"+ dayOfMonth;
            }
        }
        Date5=Date+"";


        Data3 = dataStore.getInt(Date5,0);
        Date2 = year + "/" + monthOfYears + "/" + dayOfMonth;
        BARENTRY.add(new BarEntry(Data3,0));
        BarEntryLabels.add(Date2);

        Bardataset = new BarDataSet(BARENTRY, "使用時間");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);
        chart.animateY(3000);
        if(Data3==0) {
            textView.setText("記録がありません。");
        }else {
            textView.setText(Date2 + "日の記録");
        }
       // chart.invalidate();




//        textView.setText(Date);
    }





    public void AddValuesToBARENTRY(){


    }


    public void AddValuesToBarEntryLabels(){


        //BarEntryLabels.add("一昨日");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePick();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void k(View v){
        Intent intent = new Intent(this, RActivity.class);
        intent.putExtra("keyword",Date);
        startActivity(intent);
    }



}

