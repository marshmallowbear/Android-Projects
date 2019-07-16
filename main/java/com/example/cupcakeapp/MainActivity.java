package com.example.cupcakeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskCompleted, View.OnClickListener{
    Button fetchButton;
    TextView urlTextView;
    AsyncTaskDownload astd;
    ProgressBar pb;
    int[] imageViewIds;
    TextView downloadTextView;
    ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<Bitmap>();

        fetchButton = findViewById(R.id.fetchButton);
        urlTextView = findViewById(R.id.urlTextEdit);
        pb = findViewById(R.id.progressBar);
        downloadTextView = findViewById(R.id.downloadTextView);
        astd = null;
        imageViewIds = new int[20];
        imageViewIds[0] = R.id.imageView1;
        imageViewIds[1] = R.id.imageView2;
        imageViewIds[2] = R.id.imageView3;
        imageViewIds[3] = R.id.imageView4;

        imageViewIds[4] = R.id.imageView5;
        imageViewIds[5] = R.id.imageView6;
        imageViewIds[6] = R.id.imageView7;
        imageViewIds[7] = R.id.imageView8;

        imageViewIds[8] = R.id.imageView9;
        imageViewIds[9] = R.id.imageView10;
        imageViewIds[10] = R.id.imageView11;
        imageViewIds[11] = R.id.imageView12;

        imageViewIds[12] = R.id.imageView13;
        imageViewIds[13] = R.id.imageView14;
        imageViewIds[14] = R.id.imageView15;
        imageViewIds[15] = R.id.imageView16;

        imageViewIds[16] = R.id.imageView17;
        imageViewIds[17] = R.id.imageView18;
        imageViewIds[18] = R.id.imageView19;
        imageViewIds[19] = R.id.imageView20;


//        ImageView iv = findViewById(R.id.imageView);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AsyncTaskDownload astd = new AsyncTaskDownload(MainActivity.this, R.id.imageView);
//                astd.execute("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
//            }
//        });

    }

    public void onFetch(View v){
        if(astd != null){//means some task running
            astd.cancel(true);
            removeImages();
            pb.setVisibility(urlTextView.GONE);
            fetch();
            //abort and refetch
        }
        else{
            fetch();
        }
    }

    private void removeImages() {
        for(int imgId : imageViewIds){
            ImageView iv = findViewById(imgId);
            iv.setImageBitmap(null);
        }
    }

    private void fetch() {
        astd = new AsyncTaskDownload(MainActivity.this, MainActivity.this, imageViewIds);
        urlTextView = findViewById(R.id.urlTextEdit);
        String urlStr = urlTextView.getText().toString();
        /*astd.execute("https://i.ibb.co/kmKNyx0/sparkle-Cupcake.jpg","https://i.ibb.co/z65Y7qY/chaicupcake.jpg", "https://i.ibb.co/xLMSBY2/chocolatecupcake.png", "https://i.ibb.co/Cwk2Q7t/pink-Cupcake.jpg",
                "https://i.ibb.co/kmKNyx0/sparkle-Cupcake.jpg","https://i.ibb.co/z65Y7qY/chaicupcake.jpg", "https://i.ibb.co/xLMSBY2/chocolatecupcake.png", "https://i.ibb.co/Cwk2Q7t/pink-Cupcake.jpg",
                "https://i.ibb.co/kmKNyx0/sparkle-Cupcake.jpg","https://i.ibb.co/z65Y7qY/chaicupcake.jpg", "https://i.ibb.co/xLMSBY2/chocolatecupcake.png", "https://i.ibb.co/Cwk2Q7t/pink-Cupcake.jpg",
                "https://i.ibb.co/kmKNyx0/sparkle-Cupcake.jpg","https://i.ibb.co/z65Y7qY/chaicupcake.jpg", "https://i.ibb.co/xLMSBY2/chocolatecupcake.png", "https://i.ibb.co/Cwk2Q7t/pink-Cupcake.jpg",
                "https://i.ibb.co/kmKNyx0/sparkle-Cupcake.jpg","https://i.ibb.co/z65Y7qY/chaicupcake.jpg", "https://i.ibb.co/xLMSBY2/chocolatecupcake.png", "https://i.ibb.co/Cwk2Q7t/pink-Cupcake.jpg");*/
        astd.execute("https://stocksnap.io/search/cupcake");
    }


    @Override
    public void updateComplete() {
        astd = null;
        //call ws method
        setOnClickListenersToImages();
    }

    private void setOnClickListenersToImages() {
        ImageView[] ImageViews = new ImageView[]{
                findViewById(R.id.imageView1),
        findViewById(R.id.imageView2),
        findViewById(R.id.imageView3),
        findViewById(R.id.imageView4),
        findViewById(R.id.imageView5),
        findViewById(R.id.imageView6),
        findViewById(R.id.imageView7),
        findViewById(R.id.imageView8),
        findViewById(R.id.imageView9),
        findViewById(R.id.imageView10),
        findViewById(R.id.imageView11),
        findViewById(R.id.imageView12),
        findViewById(R.id.imageView13),
                findViewById(R.id.imageView14),
        findViewById(R.id.imageView15),
        findViewById(R.id.imageView16),
        findViewById(R.id.imageView17),
        findViewById(R.id.imageView18),
        findViewById(R.id.imageView19),
        findViewById(R.id.imageView20)
        };
        for(ImageView iv : ImageViews){
            iv.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView1:
                ImageView iv1 = findViewById(R.id.imageView1);
                Bitmap bmp1 = ((BitmapDrawable) iv1.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp1)) {
                    images.add(bmp1);
                    //show tick
                    ImageView tick = findViewById(R.id.tick1);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp1);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick1);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 1) {
                    sendImages();
//                    Intent intent = new Intent(this, GameActivity.class);
//                    intent.putExtra("bitmap1", images.get(0));
//                    intent.putExtra("bitmap2", images.get(1));
//                    intent.putExtra("bitmap3", images.get(2));
//                    intent.putExtra("bitmap4", images.get(3));
//                    intent.putExtra("bitmap5", images.get(4));
//                    intent.putExtra("bitmap6", images.get(5));
//                    startActivity(intent);
                }
                break;
            case R.id.imageView2:
                ImageView iv2 = findViewById(R.id.imageView2);
                Bitmap bmp2 = ((BitmapDrawable) iv2.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp2)) {
                    images.add(bmp2);
                    //show tick
                    ImageView tick = findViewById(R.id.tick2);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp2);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick2);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView3:
                ImageView iv3 = findViewById(R.id.imageView3);
                Bitmap bmp3 = ((BitmapDrawable) iv3.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp3)) {
                    images.add(bmp3);
                    //show tick
                    ImageView tick = findViewById(R.id.tick3);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp3);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick3);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView4:
                ImageView iv4 = findViewById(R.id.imageView4);
                Bitmap bmp4 = ((BitmapDrawable) iv4.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp4)) {
                    images.add(bmp4);
                    //show tick
                    ImageView tick = findViewById(R.id.tick4);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp4);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick4);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView5:
                ImageView iv5 = findViewById(R.id.imageView5);
                Bitmap bmp5 = ((BitmapDrawable) iv5.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp5)) {
                    images.add(bmp5);
                    //show tick
                    ImageView tick = findViewById(R.id.tick5);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp5);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick5);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView6:
                ImageView iv6 = findViewById(R.id.imageView6);
                Bitmap bmp6 = ((BitmapDrawable) iv6.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp6)) {
                    images.add(bmp6);
                    //show tick
                    ImageView tick = findViewById(R.id.tick6);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp6);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick6);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView7:
                ImageView iv7 = findViewById(R.id.imageView7);
                Bitmap bmp7 = ((BitmapDrawable) iv7.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp7)) {
                    images.add(bmp7);
                    //show tick
                    ImageView tick = findViewById(R.id.tick7);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp7);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick7);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView8:
                ImageView iv8 = findViewById(R.id.imageView8);
                Bitmap bmp8 = ((BitmapDrawable) iv8.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp8)) {
                    images.add(bmp8);
                    //show tick
                    ImageView tick = findViewById(R.id.tick8);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp8);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick8);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView9:
                ImageView iv9 = findViewById(R.id.imageView9);
                Bitmap bmp9 = ((BitmapDrawable) iv9.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp9)) {
                    images.add(bmp9);
                    //show tick
                    ImageView tick = findViewById(R.id.tick9);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp9);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick9);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView10:
                ImageView iv10 = findViewById(R.id.imageView10);
                Bitmap bmp10 = ((BitmapDrawable) iv10.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp10)) {
                    images.add(bmp10);
                    //show tick
                    ImageView tick = findViewById(R.id.tick10);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp10);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick10);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView11:
                ImageView iv11 = findViewById(R.id.imageView11);
                Bitmap bmp11 = ((BitmapDrawable) iv11.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp11)) {
                    images.add(bmp11);
                    //show tick
                    ImageView tick = findViewById(R.id.tick11);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp11);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick11);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView12:
                ImageView iv12 = findViewById(R.id.imageView12);
                Bitmap bmp12 = ((BitmapDrawable) iv12.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp12)) {
                    images.add(bmp12);
                    //show tick
                    ImageView tick = findViewById(R.id.tick12);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp12);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick12);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView13:
                ImageView iv13 = findViewById(R.id.imageView13);
                Bitmap bmp13 = ((BitmapDrawable) iv13.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp13)) {
                    images.add(bmp13);
                    //show tick
                    ImageView tick = findViewById(R.id.tick13);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp13);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick13);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView14:
                ImageView iv14 = findViewById(R.id.imageView14);
                Bitmap bmp14 = ((BitmapDrawable) iv14.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp14)) {
                    images.add(bmp14);
                    //show tick
                    ImageView tick = findViewById(R.id.tick14);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp14);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick14);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView15:
                ImageView iv15 = findViewById(R.id.imageView15);
                Bitmap bmp15 = ((BitmapDrawable) iv15.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp15)) {
                    images.add(bmp15);
                    //show tick
                    ImageView tick = findViewById(R.id.tick15);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp15);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick15);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView16:
                ImageView iv16 = findViewById(R.id.imageView16);
                Bitmap bmp16 = ((BitmapDrawable) iv16.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp16)) {
                    images.add(bmp16);
                    //show tick
                    ImageView tick = findViewById(R.id.tick16);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp16);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick16);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView17:
                ImageView iv17 = findViewById(R.id.imageView17);
                Bitmap bmp17 = ((BitmapDrawable) iv17.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp17)) {
                    images.add(bmp17);
                    //show tick
                    ImageView tick = findViewById(R.id.tick17);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp17);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick17);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView18:
                ImageView iv18 = findViewById(R.id.imageView18);
                Bitmap bmp18 = ((BitmapDrawable) iv18.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp18)) {
                    images.add(bmp18);
                    //show tick
                    ImageView tick = findViewById(R.id.tick18);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp18);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick18);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView19:
                ImageView iv19 = findViewById(R.id.imageView19);
                Bitmap bmp19 = ((BitmapDrawable) iv19.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp19)) {
                    images.add(bmp19);
                    //show tick
                    ImageView tick = findViewById(R.id.tick19);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp19);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick19);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
            case R.id.imageView20:
                ImageView iv20 = findViewById(R.id.imageView20);
                Bitmap bmp20 = ((BitmapDrawable) iv20.getDrawable()).getBitmap();
                if (images.size() < 6 && !images.contains(bmp20)) {
                    images.add(bmp20);
                    //show tick
                    ImageView tick = findViewById(R.id.tick20);
                    tick.setVisibility(View.VISIBLE);
                } else {
                    images.remove(bmp20);
                    //hide tick
                    ImageView tick = findViewById(R.id.tick20);
                    tick.setVisibility(View.VISIBLE);
                }
                if (images.size() == 6) {
                    //sendImages();
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("bitmap1", images.get(0));
                    intent.putExtra("bitmap2", images.get(1));
                    intent.putExtra("bitmap3", images.get(2));
                    intent.putExtra("bitmap4", images.get(3));
                    intent.putExtra("bitmap5", images.get(4));
                    intent.putExtra("bitmap6", images.get(5));
                    startActivity(intent);
                }
                break;
        }

    }

    private void sendImages() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("bitmap1", images.get(0));
        /*intent.putExtra("bitmap2", images.get(1));
        intent.putExtra("bitmap3", images.get(2));
        intent.putExtra("bitmap4", images.get(3));
        intent.putExtra("bitmap5", images.get(4));
        intent.putExtra("bitmap6", images.get(5));*/
        startActivity(intent);
    }

}
