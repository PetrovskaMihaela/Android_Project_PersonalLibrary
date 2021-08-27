package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    String username = "";
    TextView user;
    EditText s1, s2, s3;
    ImageButton b;
    DatabaseHelper db;
    Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        user = findViewById(R.id.username_home);
        s1 = findViewById(R.id.goalbooks);
        s2 = findViewById(R.id.goalhours);
        s3 = findViewById(R.id.goalpages);
        b = findViewById(R.id.goals_save);
        db = new DatabaseHelper(this);
        goal = new Goal();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
        }
        user.setText(username);

        String v1 = s1.getText().toString();
        String v2 = s2.getText().toString();
        String v3 = s3.getText().toString();

        goal.setUser(username);
        goal.setBooks(Integer.parseInt(v1));
        goal.setHours(Integer.parseInt(v2));
        goal.setPages(Integer.parseInt(v3));

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goal.setUser(username);
                goal.setBooks(Integer.parseInt(s1.getText().toString()));
                goal.setHours(Integer.parseInt(s2.getText().toString()));
                goal.setPages(Integer.parseInt(s3.getText().toString()));
                boolean check = db.checkGoal(username);
                if(check){

                    boolean insert = db.insertGoal(goal);
                    if (insert) {
                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    db.updateGoal(goal);
                    Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    public void ClickMenu(View view){
        MyProfile.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MyProfile.closeDrawer(drawerLayout);
    }

    public void ClickBrowser(View view){
        MyProfile.redirectActivity(this, BrowseBooks.class);

    }
    public void ClickReading(View view){
        MyProfile.redirectActivity(this, ReadingActivity.class);
    }

    public void ClickLibrary(View view){
        MyProfile.redirectActivity(this, YourLibrary.class);
    }
    public void ClickProfile(View view){
        MyProfile.redirectActivity(this, MyProfile.class);
    }

    public void ClickLogout(View view){
        MyProfile.logout(this);
    }
    public void ClickAdd(View view){
        Intent i = new Intent(Home.this, AddBook.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    public void ClickHome(View view){
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyProfile.closeDrawer(drawerLayout);
    }
}