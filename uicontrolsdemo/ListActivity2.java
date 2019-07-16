package com.iss.uicontrolsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity2 extends AppCompatActivity
    implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        Bundle extras = getIntent().getExtras();
        String[] values = extras.getStringArray("list");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row, R.id.textView, values);
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long id)
    {
        String item = (String) av.getItemAtPosition(pos);
        Toast.makeText(this, item + " selected",
                Toast.LENGTH_LONG).show();
    }
}
