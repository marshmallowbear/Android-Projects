package com.iss.uicontrolsdemo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    String[] fruits = new String[] {
            "Apple",
            "Orange",
            "Pear",
            "Blueberry",
            "Banana",
            "Mango",
            "Strawberry",
            "Apricot",
            "Durian",
            "Lemon"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toggle_chkbox = findViewById(R.id.toggle_chkbox);
        toggle_chkbox.setOnClickListener(this);

        Button long_press_btn = findViewById(R.id.long_press_btn);
        registerForContextMenu(long_press_btn);

        Button listview1_btn = findViewById(R.id.listview1);
        listview1_btn.setOnClickListener(this);

        Button listview2_btn = findViewById(R.id.listview2);
        listview2_btn.setOnClickListener(this);

        Button listview3_btn = findViewById(R.id.listview3);
        listview3_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int requestCode;
        Intent intent = null;

        switch (v.getId()) {
            case R.id.checkBox1:
                CheckBox checkbox1 = findViewById(R.id.checkBox1);
                checkbox1.toggle();
                break;

            case R.id.listview1:
                requestCode = 1;
                intent = new Intent(this, ListActivity1.class);
                intent.putExtra("list", fruits);
                startActivityForResult(intent, requestCode);
                break;

            case R.id.listview2:
                requestCode = 2;
                intent = new Intent(this, ListActivity2.class);
                intent.putExtra("list", fruits);
                startActivityForResult(intent, requestCode);
                break;

            case R.id.listview3:
                intent = new Intent(this, ListActivity3.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_dialog:
                showAlert(getString(R.string.alertTitle), getString(R.string.alertMsg));
                return true;

            case R.id.say_hello:
                String helloMsg = getString(R.string.hello_msg);
                Toast t = Toast.makeText(this, helloMsg, Toast.LENGTH_LONG);
                t.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showAlert(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String selected = null;
        switch (item.getItemId()) {
            case R.id.menu_item_upload:
                selected = getString(R.string.upload);
                break;
            case R.id.menu_item_search:
                selected = getString(R.string.search);
                break;
        }
        if (selected != null) {
            Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
