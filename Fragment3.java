package teityan.com.appkn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import static android.content.Context.MODE_PRIVATE;

public class Fragment3 extends Fragment {
    ImageButton go;
    SharedPreferences dataStore;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, null);
        go = (ImageButton)view.findViewById(R.id.go);
        super.onCreate(savedInstanceState);


        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceTimer.class);
                getActivity().startService(intent);
                Intent intenta = new Intent(getActivity(), MainActivity.class);
                startActivity(intenta);
                /*dataStore = getSharedPreferences("DataStore",MODE_PRIVATE);
                SharedPreferences.Editor editor = dataStore.edit();
                editor.putInt("start", 1);
                editor.apply();*/



    }});
        return view;
}
}