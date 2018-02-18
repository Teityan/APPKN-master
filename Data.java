package teityan.com.appkn;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tenko_w8othx0 on 2018/02/18.
 */

public class Data extends Application {
    String pk[][];
    Date nowDate = new Date();
    Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
    data1 = new SimpleDateFormat("yyyyMMdd",Locale.JAPAN).format(cal.getTime());
    public Data() {

    }


    public String up() {


        return result;
    }

    public void pakege() {
        final PackageManager pm = getPackageManager();
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        int count=0;
        for (PackageInfo info : pm.getInstalledPackages(flags)) {

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)
                continue;

           String  pks = info.packageName;
           String pkn =info.applicationInfo.loadLabel(pm).toString();
           pk[count]=new String[]{pks,pkn};
        count++;
        }
        }
    }
