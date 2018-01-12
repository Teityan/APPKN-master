package teityan.com.appkn;

import android.util.Log;

/**
 * Created by tenko_w8othx0 on 2018/01/07.
 */

public class Post {
    String name;
    String time;



    public Post() {
    }
    public Post(String name,String time){
        this.name=name;
        this.time=time;
        Log.d("test","test");
    }
    public String getName(){
        return name;
    }
    public void setName(String name){this.name=name;}

    public String getTime(){return time;}

    public void setTime(String time){this.time=time;}


}

