package com.example.cupcakeapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bitmap bm1 = getIntent().getParcelableExtra("bitmap1");
        ImageView iv = findViewById(R.id.sentImage1);
        iv.setImageBitmap(bm1);

       /* Bitmap bm2 = getIntent().getParcelableExtra("bitmap2");
        ImageView iv2 = findViewById(R.id.sentImage2);
        iv2.setImageBitmap(bm2);

        Bitmap bm3 = getIntent().getParcelableExtra("bitmap3");
        ImageView iv3 = findViewById(R.id.sentImage3);
        iv3.setImageBitmap(bm3);

        Bitmap bm4 = getIntent().getParcelableExtra("bitmap4");
        ImageView iv4 = findViewById(R.id.sentImage4);
        iv4.setImageBitmap(bm4);

        Bitmap bm5 = getIntent().getParcelableExtra("bitmap5");
        ImageView iv5 = findViewById(R.id.sentImage5);
        iv5.setImageBitmap(bm5);

        Bitmap bm6 = getIntent().getParcelableExtra("bitmap6");
        ImageView iv6 = findViewById(R.id.sentImage6);
        iv6.setImageBitmap(bm6);*/
    }
}
