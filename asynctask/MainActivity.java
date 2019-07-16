package com.iss.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            File file = new File(getFilesDir() + "/camp.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ImageView img = findViewById(R.id.imageView);
            img.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url = "https://images.pexels.com/photos/1413683/pexels-photo-1413683.jpeg?cs=srgb&dl=4k-wallpaper-daylight-environment-1413683.jpg&fm=jpg";
        String fpath = getFilesDir() + "/relax.jpg";
        new MyAsyncTask(new WeakReference<AppCompatActivity>(this)).execute(url, fpath);
    }

    public void testProgressBar() {
        new Thread(new Runnable() {
            ProgressBar bar = findViewById(R.id.progressBar);

            public void run() {
                for (int i=0; i<100; i++)
                {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    progress = i;
                    handler.post(new Runnable() {
                        public void run() {
                            bar.setProgress(progress);
                        }
                    }) ;
                }
            }
        }).start();
    }
}
