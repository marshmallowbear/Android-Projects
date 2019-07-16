package com.example.cupcakestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] cupcake_names;
    String[] cupcake_descriptions;
    int [] cupcake_image_ids;
    ListView listView;
    MenuInflater menuInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cupcake_names = getResources().getStringArray(R.array.cupcake_names);
        cupcake_descriptions = getResources().getStringArray(R.array.cupcake_descriptions);
        cupcake_image_ids = new int[] {R.drawable.sparklecupcake, R.drawable.chaicupcake, R.drawable.pinkcupcake, R.drawable.chocolatecupcake};
        listView = findViewById(R.id.listView);

        listView.setAdapter(new CustomAdapter(this, R.layout.list_row, cupcake_names, cupcake_descriptions, cupcake_image_ids));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_1:
                break;
            case R.id.menu_item_2:
                Toast.makeText(this, R.string.menu_toast_2,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_3:
                Toast.makeText(this, R.string.menu_toast_2,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_4:
                Toast.makeText(this, R.string.menu_toast_4, Toast.LENGTH_LONG).show();
                break;

        }
        return true;
        //return super.onOptionsItemSelected(item);
    }
}
