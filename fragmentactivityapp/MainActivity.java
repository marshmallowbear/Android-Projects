package com.example.fragmentactivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OptionsFragment.CallbackOptionsFragment {

    int[] imageResIds = new int[4];
    TextView description;

    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.descrTextView);
        description.setText(R.string.select_cupcake);

        //initialise imageIds array
        imageResIds[0] = R.drawable.chaicupcake;
        imageResIds[1] = R.drawable.chocolatecupcake;
        imageResIds[2] = R.drawable.pinkcupcake;
        imageResIds[3] = R.drawable.sparklecupcake;

        //dynamically add options fragment
        OptionsFragment of = new OptionsFragment(imageResIds);
        of.setActivity(this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.placeholder, of).commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof  OptionsFragment){
            //can cast
            OptionsFragment of = (OptionsFragment)fragment;
            of.setActivity(this);
        }
    }


    @Override
    public void getChosen(String prize) {
        //change description textview
        description.setText(R.string.you_have_won);
        //change fragment call fragment manager
        PrizeFragment pf = new PrizeFragment(prize);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, pf);
        ft.commit();
    }
}
