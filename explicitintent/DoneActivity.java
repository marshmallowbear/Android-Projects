package com.iss.explicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class DoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Intent data = getIntent();
        String candidate = data.getStringExtra("candidate");
        int color = data.getIntExtra("color", 0);

        Button btn = findViewById(R.id.button);
        btn.setText(candidate);
        btn.setBackgroundColor(color);
    }
}
