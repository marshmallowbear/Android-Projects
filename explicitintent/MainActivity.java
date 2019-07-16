package com.iss.explicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    static final int REQUEST_CODE_LOGIN = 1;
    static final int REQUEST_CODE_VOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.voteNowBtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                String voteId = data.getStringExtra("voteId");
                Intent vote = new Intent(this, VoteActivity.class);
                vote.putExtra("voteId", voteId);
                startActivityForResult(vote, REQUEST_CODE_VOTE);
                break;

            case REQUEST_CODE_VOTE:
                String candidate = data.getStringExtra("candidate");
                int color = data.getIntExtra("color", 0);

                Intent done = new Intent(this, DoneActivity.class);
                done.putExtra("candidate", candidate);
                done.putExtra("color", color);
                startActivity(done);
                break;
        }
    }
}