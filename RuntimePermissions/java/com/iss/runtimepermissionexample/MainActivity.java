package com.iss.runtimepermissionexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    protected final int WRITE_EXTERNAL_STORAGE_REQ = 1;
    String data = "hello\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        prepareWrite();
    }

    protected void prepareWrite() {
        String[] permissions = new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (ContextCompat.checkSelfPermission(this,
                permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            writeToExtStg(data);
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    permissions, WRITE_EXTERNAL_STORAGE_REQ);
        }
    }

    protected void writeToExtStg(String data) {
        File file = Environment.getExternalStorageDirectory();
        String path = file.getAbsolutePath() + "/data.txt";

        FileWriter writer = null;
        try {
            writer = new FileWriter(path, true);
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQ:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        writeToExtStg(data);
                    }
                }
                break;
        }
    }
}
