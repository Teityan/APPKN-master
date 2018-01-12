package teityan.com.appkn;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment {
    private SharedPreferences dataStore;
    EditText editText;
    private Button buttonWrite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, null);
 /*buttonWrite = (Button)view.findViewById(R.id.button_write);
        final TextView textReads=(TextView)view.findViewById(R.id.textRead);
        dataStore = getActivity().getSharedPreferences("Name", MODE_PRIVATE);
        editText = (EditText)view.findViewById(R.id.edit_text);
        textReads.setText("名前を教えて下さい。");


        buttonWrite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text = editText.getText().toString();
                if(text!=""){
                    // 入力文字列を"input"に書き込む
          SharedPreferences.Editor editor = dataStore.edit();
           editor.putString("input", text);
                editor.commit();
                String str = dataStore.getString("input", "Nothing");
                if(!str.equals("Nothing")) {
                    textReads.setText(str+"さんで登録します。\nよかったらスワイプしてください。");
                }
            }else {
                    textReads.setText("名前が入力されていません");
                }
            }
        });
*/
        return view;
    }
}