package com.example.fragmentactivityapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class OptionsFragment extends Fragment implements View.OnClickListener {
    CallbackOptionsFragment activity;

    int[] imageResIds;

    //not sure if I can do this here
    String[] prizes;

    public OptionsFragment(int[] imageResIds){
        this.imageResIds = imageResIds;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_options, container, false);
        ImageView iv1 = v.findViewById(R.id.imageView1);
        iv1.setOnClickListener(this);
        ImageView iv2 = v.findViewById(R.id.imageView2);
        iv2.setOnClickListener(this);
        ImageView iv3 = v.findViewById(R.id.imageView3);
        iv3.setOnClickListener(this);
        ImageView iv4 = v.findViewById(R.id.imageView4);
        iv4.setOnClickListener(this);

        iv1.setImageResource(imageResIds[0]);
        iv2.setImageResource(imageResIds[1]);
        iv3.setImageResource(imageResIds[2]);
        iv4.setImageResource(imageResIds[3]);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prizes = getResources().getStringArray(R.array.prize_names);
    }

    public void setActivity(CallbackOptionsFragment Activity){
        this.activity = Activity;
    }

    @Override
    public void onClick(View view) {
        //set the prize
        //callback
        switch(view.getId()){
            case R.id.imageView1:
                activity.getChosen(prizes[0]);
                break;
            case R.id.imageView2:
                activity.getChosen(prizes[1]);
                break;
            case R.id.imageView3:
                activity.getChosen(prizes[2]);
                break;
            case R.id.imageView4:
                activity.getChosen(prizes[3]);
        }
    }


    public interface CallbackOptionsFragment{

        void getChosen(String prize);

    }
}
