package com.iss.explicitintent;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VoteActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        for (int i=0; i<4; i++) {
            int id = getResources().getIdentifier("btn" + i,
                    "id", getPackageName());
            Button btn = findViewById(id);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        ColorDrawable bkgd = (ColorDrawable) btn.getBackground();

        Intent data = new Intent();
        data.putExtra("candidate", btn.getText().toString());
        data.putExtra("color", bkgd.getColor());
        setResult(RESULT_OK, data);

        finish();
    }
}
