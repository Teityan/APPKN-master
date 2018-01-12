package teityan.com.appkn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class Fragment0 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment0, null);
    }
}