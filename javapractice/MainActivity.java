package com.example.javapractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListFragment.listFragmentCallback {

    Button b;
    TextView tv;
    EditText input;
    List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise view widgets first
        b = findViewById(R.id.button1);
        tv = findViewById(R.id.textView1);
        input = findViewById(R.id.editText1);

        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String urlStr = input.getText().toString();
        //books = Book.readBook(urlStr);

        books = new ArrayList<Book>();
        books.add(new Book("Pink Cupcakes and Strawberry Roses", "JK Rowling", "Fatastic! I love it!", "$15.90"));
        books.add(new Book("Blue Buttercream Flowers and Cupcakes", "Majesty", "Such a sweet book! I cant put the book down", "$20.90" ));
        books.add(new Book("Sunshine Buttercups and Yellow Marigold", "Princess", "Makes me so happy. I'll definitely buy this book.", "$35"));

        //create intent pass to next activity
        Intent intent = new Intent(this, Activity2.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("books", (ArrayList<Book>)books);
        intent.putExtras(bundle);
        //startActivity(intent);

        //create data pass to a fragment
        ListFragment listFrag = new ListFragment();
        listFrag.setContext(this);
        listFrag.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, listFrag).commit();
    }

    @Override
    public void getSelected(Book book) {
        //show review fragment
        ReviewFragment revFrag = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString("review", book.getReview());
        revFrag.setArguments(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, revFrag).commit();

    }
}
