package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class YourLibrary extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RecyclerView rvBooks;
    String username = "";
    TextView user;
    ylAdapter adapter;
    List<BookA> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_library);
        drawerLayout = findViewById(R.id.drawer_layout);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
        }

        user = findViewById(R.id.username_lib);
        user.setText(username);

        DatabaseHelper db = new DatabaseHelper(this);


        books = db.getBooks();

        rvBooks = findViewById(R.id.listOfBooks);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ylAdapter(this,books);
        rvBooks.setAdapter(adapter);

    }
    public void ClickAdd(View view){
        Intent i = new Intent(YourLibrary.this, AddBook.class);
        i.putExtra("username", username);
        startActivity(i);
    }
    public void ClickReading(View view){
        MyProfile.redirectActivity(this, ReadingActivity.class);
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

    public void ClickLibrary(View view){
        recreate();
    }
    public void ClickProfile(View view){
        MyProfile.redirectActivity(this, MyProfile.class);
    }

    public void ClickHome(View view){
        MyProfile.redirectActivity(this, Home.class);
    }

    public void ClickLogout(View view){
        MyProfile.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyProfile.closeDrawer(drawerLayout);
    }


}