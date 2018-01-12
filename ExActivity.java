package teityan.com.appkn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ExActivity extends AppCompatActivity {
    int data1 = 0;
    TextView contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
        contents = findViewById(R.id.textView24);

        Random r = new Random();
        data1 = r.nextInt(5);
        if (data1 == 0) {
            setTitle("①<目の休憩をしよう！！>");
            contents.setText("目の筋肉をほぐしてあげよう！\n" +
                    "\n(1) 一度目をギュッと閉じて、パッと開ける" +
                    "\n(2) 頭を動かさない様に、黒目を右へ" +
                    "\n(3) 同じ様に左へ黒目を動かす" +
                    "\n(4) 次に上へ動かす" +
                    "\n(5) 最後に下へ動かして、(1)へ戻る" +
                    "\n\nこれで、目のピントを合わせる筋肉のコリをほぐすことができます！");

        } else if (data1 == 1) {
            setTitle("②＜首の休憩をしよう！！＞");

            contents.setText("首の筋肉がつかれてませんか？\n\n首のストレッチをしましょう！\n\n" + "<ストレッチの仕方！>\n" +
                    "椅子に座ったままでOK!\n片手を頭の上に置いて、乗せている手の方に頭が倒れるように軽く引っ張ってみよう！\n" +
                    "\n" +
                    "首の付け根あたりがストレッチされるがわかったかな？。\n腕や肩に力が入らないようにして行うのがポイント！\n" +
                    "\n" +
                    "左右この動作20秒を目安におこなおう！" + "\nこれで首のコリがよくなるはず！");
        } else if (data1 == 2) {
            setTitle("③＜腕のストレッチをしよう！！＞");
            contents.setText("腕がつかれてない？\n\n" +
                    "まずは腕の外側を伸ばしていこう！\n手のひらを下にして腕をカラダの前に持ち上げます。" +
                    "\n反対の手で指を持ち、手のひらが自分の方に向くように倒して伸ばしましょう。\nストレッチ感を意識しながら左右おこないます。\n" +
                    "今度は腕の内側を伸ばします。\n手のひらを上にして腕をカラダの前に持ち上げます。\n反対の手で指を持ち、手のひらを反らせるようにして指が下にくるように伸ばしましょう。" +
                    "\n各20秒を目安におこないます。\nこれで腕に血がいっぱい来るね！");
        } else if (data1 == 3) {
            setTitle("④＜背中のストレッチしよう！＞");
            contents.setText("椅子に座ったまま、背伸びをしよう！\n血流の流れが良くなっていくよ！");
        } else if (data1 == 4) {
            setTitle("④＜肩甲骨のストレッチしよう！＞");
            contents.setText("椅子に座った状態で片脚の膝頭を抱えるようにして持ち、背中を丸めたら\n" +
                    "足の重みを利用して背中がストレッチされるのを感じよう！" +
                    "全身の力を抜いてそのまま20秒\n足を入れ替えも一度やろう！");
        }
    }
}
