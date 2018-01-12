package teityan.com.appkn;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {
    private SharedPreferences dataStore;
    EditText editText;
    private Button buttonWrite;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment2, null);
        buttonWrite = (Button)view.findViewById(R.id.button_write);
        final TextView textReads=(TextView)view.findViewById(R.id.textRead);
        dataStore = getActivity().getSharedPreferences("Goal", MODE_PRIVATE);
        editText = (EditText)view.findViewById(R.id.edit_text);
        textReads.setText("目標時間を設定して下さい。");



        buttonWrite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // エディットテキストのテキストを取得
                String text = editText.getText().toString();
                int time = Integer.parseInt(text);

                if(time<1440) {
                    // 入力文字列を"input"に書き込む
                    SharedPreferences.Editor editor = dataStore.edit();
                    editor.putString("input", text);
                    editor.commit();
                    String str = dataStore.getString("input", "Nothing");

                    textReads.setText(str + "分以下という目標を設定しました！");
                }else {
                    textReads.setText("大きすぎます！");


                }
            }


        });

        return view;}




}
