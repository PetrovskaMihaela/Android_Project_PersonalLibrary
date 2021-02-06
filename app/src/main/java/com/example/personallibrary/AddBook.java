package com.example.personallibrary;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddBook extends AppCompatActivity {

    EditText title, author;
    TextView finishDate;
    Calendar c;
    String today;
    Toolbar toolbar;
    private DatePickerDialog.OnDateSetListener datesetlistener;
    private static String username = "", titleI = "", authorsI = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
            titleI = extras.getString("title");
            authorsI = extras.getString("authors");
        }

        title = findViewById(R.id.add_title);
        author = findViewById(R.id.add_author);
        finishDate = findViewById(R.id.add_finish_date);

        c = Calendar.getInstance();
        today = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);

        title.setText(titleI);
        author.setText(authorsI);

        finishDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(
                        AddBook.this,
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

       /* OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AddBook.super.onBackPressed();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);*/

    }

    public void ClickDelete(View view){

        Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
    public void ClickSave(View view){

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        BookA book = new BookA(title.getText().toString(), author.getText().toString(), today ,finishDate.getText().toString());
        DatabaseHelper db = new DatabaseHelper(this);
        db.addBook(book, username);
        backToLibrary();
    }

    private void backToLibrary() {
        Intent i = new Intent(AddBook.this, YourLibrary.class );
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}