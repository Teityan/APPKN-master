package teityan.com.appkn;

import java.util.Comparator;

/**
 * Created by tenko_w8othx0 on 2018/03/04.
 */

public class Sorts implements Comparator<App> {

    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(App a, App b) {
        int no1 = a.getSyainNo();
        int no2 = b.getSyainNo();

        //こうすると社員番号の昇順でソートされる
        if (no1 < no2) {
            return 1;

        } else if (no1 == no2) {
            return 0;

        } else {
            return -1;

        }
    }

}
