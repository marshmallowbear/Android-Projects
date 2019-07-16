package com.example.javapractice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    listFragmentCallback activity;
    List<Book> books;
    ListView lvFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        lvFrag = v.findViewById(R.id.listViewFragment);

        //get the books
        books = (ArrayList<Book>)getArguments().getSerializable("books");
        lvFrag.setAdapter(new RowAdapter((Context)activity, R.layout.list_row, books));
        lvFrag.setOnItemClickListener(this);
        //return v;

        //do it asynchronously
        bindListView();
        return v;

    }

    private void bindListView() {
        new BackgroundTask().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Book item = (Book) adapterView.getItemAtPosition(i);
        activity.getSelected(item);
    }

    public void setContext(listFragmentCallback parentActivity){
        activity = parentActivity;
    }

    public interface listFragmentCallback{
        void getSelected (Book book);
    }

    private class BackgroundTask extends AsyncTask<Void, Integer, Void>{

        RowAdapter ra = new RowAdapter((Context)activity, R.layout.list_row, new ArrayList<Book>());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lvFrag.setAdapter(ra);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<books.size(); i++){
                try{
                    publishProgress(i);
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println("Exception while sleeping!");
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
            int i = values[0];
            ra.add(books.get(i));
            ra.notifyDataSetChanged();
        }
    }
}
