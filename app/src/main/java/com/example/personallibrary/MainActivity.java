package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    Button b1, b2;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.login);
        b2 = (Button) findViewById(R.id.registration);
        e1 = (EditText) findViewById(R.id.EmailAddress);
        e2 = (EditText) findViewById(R.id.Password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean check = db.emailpassword(email, password);
                if(check == true){
                    Toast.makeText(getApplicationContext(), "Successfully loged in!",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, MyProfile.class);
                    i.putExtra("username", e1.getText().toString());
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong Email or password", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
    }
}