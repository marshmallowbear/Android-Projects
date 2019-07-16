package com.iss.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button goBtn = findViewById(R.id.goBtn);
        if (goBtn != null)
            goBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String voteId = "";

        if (v.getId() == R.id.goBtn) {

            EditText editText = findViewById(R.id.voteIdEdit);
            if (editText != null)
                voteId = editText.getText().toString();

            Intent data = new Intent();
            data.putExtra("voteId", voteId);
            setResult(RESULT_OK, data);

            finish();
        }
    }
}
