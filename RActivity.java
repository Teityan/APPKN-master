package teityan.com.appkn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RActivity extends AppCompatActivity {
    String data1;
    Intent intent;
    int Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r);
        setTitle("アプリ別の使用時間");
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        String data2 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
       intent = getIntent();
       data1 = intent.getStringExtra("keyword");

        // 端末にインストール済のアプリケーション一覧情報を取得
        final PackageManager pm = getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        @SuppressLint("WrongConstant") final List<ApplicationInfo> installedAppList = pm.getInstalledApplications(flags);


        // リストに一覧データを格納する
        final List<AppData> dataList = new ArrayList<AppData>();
        for (ApplicationInfo app : installedAppList) {
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) continue;
            AppData data = new AppData();
            data.label = app.loadLabel(pm).toString();
            data.pname = app.packageName;
            data.icon = app.loadIcon(pm);


            dataList.add(data);
        }

        // リストビューにアプリケーションの一覧を表示する
        final ListView listView = new ListView(this);
        listView.setAdapter(new AppListAdapter(this, dataList));
        setContentView(listView);
    }



    // アプリケーションデータ格納クラス
    private static class AppData {
        String label;
        Drawable icon;
        String pname;
    }

    // アプリケーションのラベルとアイコンを表示するためのアダプタークラス
    private class AppListAdapter extends ArrayAdapter<AppData> {

        private final LayoutInflater mInflater;

        public AppListAdapter(Context context, List<AppData> dataList) {
            super(context, R.layout.activity_r);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            addAll(dataList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = new ViewHolder();

            if (convertView == null) {
                convertView = mInflater
                        .inflate(R.layout.activity_r, parent, false);
                holder.textLabel = convertView.findViewById(R.id.label);
                holder.imageIcon =  convertView.findViewById(R.id.icon);
                holder.text =  convertView.findViewById(R.id.pname);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // 表示データを取得
            final AppData data = getItem(position);
            // ラベルとアイコンをリストビューに設定
            //holder.textLabel.setText(data.label);
            holder.textLabel.setText(data.label);
            holder.imageIcon.setImageDrawable(data.icon);

            String name=data.pname;
            if(data1=="") {
                Date nowDate = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(nowDate);
                data1 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
            }
            SharedPreferences dataStore = getSharedPreferences(data1, MODE_PRIVATE);
            int time = dataStore.getInt(name, 0);


            holder.text.setText(time+"分");
          //  holder.text.setText(data1);

            return convertView;
        }
    }

    // ビューホルダー
    private static class ViewHolder {
        TextView textLabel;
        ImageView imageIcon;
        TextView text;
    }

}

