package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = extras.getString("username");
        }

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
        MyProfile.redirectActivity(this, YourLibrary.class);
    }
    public void ClickProfile(View view){
        MyProfile.redirectActivity(this, MyProfile.class);
    }

    public void CLickLogout(View view){
        MyProfile.logout(this);
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