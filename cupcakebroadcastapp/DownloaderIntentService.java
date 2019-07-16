package com.example.cupcakebroadcastapp;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloaderIntentService extends IntentService {

    public static final String ACTION_DOWNLOAD = "DOWNLOAD IMAGE";
    public static final String ACTION_STOP = "STOP DOWNLOAD";
    private static boolean shouldStop = false;

    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        if (action.equals(ACTION_DOWNLOAD)) {
            try {
                String urlStr = intent.getStringExtra("url");
                //convert string to URL first
                URL url = new URL(urlStr);
                intent.setAction(MainActivity.LocalBroadcastReceiver.ACTION_PROGRESS);
                intent.putExtra("value", 25);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                //get a http conn object
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                //conn.open
                conn.connect();
                intent.setAction(MainActivity.LocalBroadcastReceiver.ACTION_PROGRESS);
                intent.putExtra("value", 60);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                //start download; collect as input stream
                InputStream input = conn.getInputStream();
                intent.setAction(MainActivity.LocalBroadcastReceiver.ACTION_PROGRESS);
                intent.putExtra("value", 99);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                Bitmap result = BitmapFactory.decodeStream(input);
                if(shouldStop == false){
                    intent.setAction(MainActivity.LocalBroadcastReceiver.ACTION_DONE);
                    intent.putExtra("image", result);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                }

            } catch (Exception e) {
                System.out.print("Exception when downloading image!");

            }
        }
        stopSelf();

    }

    public DownloaderIntentService() {
        super("Image Downloader Intent Service");
    }

    public DownloaderIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String action = intent.getAction();
        switch(action){
            case ACTION_DOWNLOAD:
                shouldStop = false;
                break;
            case ACTION_STOP:
                shouldStop = true;
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
