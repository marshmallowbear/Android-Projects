package com.example.javapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    TextView tvReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        tvReview = findViewById(R.id.textViewReview);

        String review = getIntent().getStringExtra("review");
        tvReview.setText(review);
    }
}
