package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DetailsBook extends AppCompatActivity {

    EditText title, author;
    TextView finishDate;
    EditText today;
    Toolbar toolbar;
    private static String username = "";
    long bId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_book);
        toolbar = findViewById(R.id.det_toolbarr);
        setSupportActionBar(toolbar);

  /*      Bundle extras = getIntent().getExtras();
        if(extras != null) {
           // username = extras.getString("username");
            bId = extras.getLong("id");
        }
*/
        Intent i = getIntent();
        bId = i.getLongExtra("id", 0);

        DatabaseHelper db = new DatabaseHelper(this);
        BookA book = db.getBook(bId);

        title = findViewById(R.id.det_title);
        author = findViewById(R.id.det_author);
        today = findViewById(R.id.det_date);
        finishDate = findViewById(R.id.det_finish_date);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        today.setText(book.getDate());
        finishDate.setText(book.getFinishDate());



    }


    public void ClickEdit(View view){

        Intent i = new Intent(this, EditBook.class);
        i.putExtra("id", bId);
        startActivity(i);
    }

}