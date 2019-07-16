package com.example.javapractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RowAdapter extends ArrayAdapter<Book> {
    Context activity;
    int resId;
    List<Book> books;



    public RowAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        resId = resource;
        this.activity = context;
        books = objects;
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater infl = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = infl.inflate(resId, parent, false);
        TextView tvTitle = v.findViewById(R.id.textViewTitle);
        TextView tvAuthor = v.findViewById(R.id.textViewAuthor);

        try{
            tvTitle.setText(books.get(position).getTitle());
            tvAuthor.setText(books.get(position).getAuthor());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return v;
    }


}
