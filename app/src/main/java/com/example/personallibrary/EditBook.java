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

public class EditBook extends AppCompatActivity {

    EditText title, author;
    TextView finishDate;
    Calendar c;
    String today;
    Toolbar toolbar;
    private DatePickerDialog.OnDateSetListener datesetlistener;
    private static String username = "";
    long bId = 0;
    BookA book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        toolbar = findViewById(R.id.edit_toolbarr);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
         //   username = extras.getString("username");
            bId = extras.getLong("id");
        }
        DatabaseHelper db = new DatabaseHelper(this);
       book = db.getBook(bId);

        title = (EditText)findViewById(R.id.edit_title);
        author = findViewById(R.id.edit_author);
        finishDate = findViewById(R.id.edit_finish_date);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        finishDate.setText(book.getFinishDate());

        c = Calendar.getInstance();
        today = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);

        finishDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(
                        EditBook.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        datesetlistener,
                        year, month, day

                );
                picker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                picker.show();
            }

        });

        datesetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                finishDate.setText(date);
            }
        };


    }

    public void ClickEditDelete(View view){

        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
        DatabaseHelper db = new DatabaseHelper(this);
        db.deleteBook(bId);
        backToLibrary();
    }
    public void ClickEditSave(View view){

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        BookA book = new BookA(bId ,title.getText().toString(), author.getText().toString(), today ,finishDate.getText().toString());
        DatabaseHelper db = new DatabaseHelper(this);
        db.editBook(book, username);
        backToLibrary();
    }

    private void backToLibrary() {
        Intent i = new Intent(EditBook.this, YourLibrary.class );
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}