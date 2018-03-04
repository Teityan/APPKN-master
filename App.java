package teityan.com.appkn;

/**
 * Created by tenko_w8othx0 on 2018/03/04.
 */

public class App {

    private int time;
    private String appName;

    //コンストラクタ
    public App(int time, String appname) {
        this.time = time;
        this.appName = appname;
    }

    //社員番号取得
    public int getSyainNo(){
        return this.time;
    }

    //社員名取得
    public String getSyainName(){
        return this.appName;
    }

}
