package com.example.cupcakestoreapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
    private String[] names;
    private String[] descriptions;
    private int[] images;
    LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, String[] names, String[] descriptions, int[]images) {
        super(context, resource);
        this.names = names;
        this.descriptions = descriptions;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get inflater first
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate the view by converting xml into pojo
        View v = inflater.inflate(R.layout.list_row, parent, false);
        ImageView iv = v.findViewById(R.id.imageView);
        TextView titleTV = v.findViewById(R.id.textViewTitle);
        TextView descrTV = v.findViewById(R.id.textViewDesc);

        //set image
        //iv.setImageDrawable(v.getResources().getDrawable(images[position], null));
        iv.setImageResource(images[position]);
        //set Title
        titleTV.setText(names[position]);
        //set Descr
        descrTV.setText(descriptions[position]);

        return v;
    }

    @Override
    public int getCount() {
        return names.length;
    }
    

}
