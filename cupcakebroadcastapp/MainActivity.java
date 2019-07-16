package com.example.cupcakebroadcastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Button cancelButton;
    ImageView imgView;
    LocalBroadcastReceiver localBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar);
        cancelButton = findViewById(R.id.cancelButton);
        imgView = findViewById(R.id.imageView);
        //localbroadcastreceiver instantiate later on set receiver
    }

    public void onClickCancel(View w){
        pb.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        Intent intent = new Intent(this, DownloaderIntentService.class);
        intent.setAction(DownloaderIntentService.ACTION_STOP);
        startService(intent);

    }

    public void setReceiver(){
        localBroadcastReceiver = new LocalBroadcastReceiver();
        IntentFilter IF = new IntentFilter();
        IF.addAction(LocalBroadcastReceiver.ACTION_PROGRESS);
        IF.addAction(LocalBroadcastReceiver.ACTION_DONE);
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver, IF);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(localBroadcastReceiver);
    }

    public void downloadImage(View v){
        pb.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, DownloaderIntentService.class);
        intent.setAction(DownloaderIntentService.ACTION_DOWNLOAD);
        intent.putExtra("url", "https://i.ibb.co/WBMDZpn/chaicupcake.jpg");
        //call broadcast receiver instead of intentservice directly
        startService(intent);
    }

    public class LocalBroadcastReceiver extends BroadcastReceiver{
        public static final String ACTION_PROGRESS = "PROGRESS UPDATE";
        public static final String ACTION_DONE = "DOWNLOAD COMPLETE";
        @Override
        public void onReceive(Context context, Intent intent) {
           String action = intent.getAction();
           if(action == null){
               return;
           }
           switch (action){
               case ACTION_PROGRESS:
                   Bundle extras_progress = intent.getExtras();
                   int value = (int)extras_progress.get("value");
                   pb.setProgress(value);
                   //update ui on progress
                   break;
               case ACTION_DONE:
                   //update ui picture
                   Bundle extras_done = intent.getExtras();
                   Bitmap bitmapImg = (Bitmap)extras_done.get("image");
                   pb.setVisibility(View.GONE);
                   cancelButton.setVisibility(View.GONE);
                   imgView.setImageBitmap(bitmapImg);
                   break;
           }

        }
    }

}
