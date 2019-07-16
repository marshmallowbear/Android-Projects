package com.example.javapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle bundle = getIntent().getExtras();
        ArrayList<Book> books = (ArrayList<Book>)getIntent().getSerializableExtra("books");
        lv = findViewById(R.id.listView);
        lv.setAdapter(new RowAdapter(this, R.layout.list_row, books));

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Book item = (Book)adapterView.getItemAtPosition(i);

        String review = item.getReview();

        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("review", review);
        startActivity(intent);

    }
}
