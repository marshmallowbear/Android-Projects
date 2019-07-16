package com.iss.permissionsworkshop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    protected final int WRITE_EXTERNAL_STORAGE = 0;
    protected final int RECEIVE_SMS = 1;
    protected final int RECORD_AUDIO = 2;

    protected final int[] reqs = {
            WRITE_EXTERNAL_STORAGE,
            RECEIVE_SMS,
            RECORD_AUDIO
    };

    String[] toGetPermissions = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.RECORD_AUDIO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doRequest(0);
    }

    protected void doRequest(int req) {
        if (req < reqs.length)
            askForPermissions(req);
    }

    protected void askForPermissions(int runno) {
        if (ContextCompat.checkSelfPermission(this,
                toGetPermissions[runno]) == PackageManager.PERMISSION_GRANTED) {
            System.out.println(toGetPermissions[runno] + " already granted.");
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{toGetPermissions[runno]}, reqs[runno]);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        System.out.println("requestCode = " + requestCode);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                System.out.println(toGetPermissions[requestCode] + " is granted.");
            }
        }

        doRequest(requestCode + 1);
    }
}
