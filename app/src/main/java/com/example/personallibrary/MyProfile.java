package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    private static String username = "";
    DrawerLayout drawerlayout;

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user = findViewById(R.id.username_prof);
        drawerlayout = findViewById(R.id.drawer_layout);

        Intent i = getIntent();
        username = i.getStringExtra("username");

        user.setText(username);
    }

    public void ClickMenu(View view){
        openDrawer(drawerlayout);

    }

    public static void openDrawer(DrawerLayout drawerlayout) {

        drawerlayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerlayout);
    }

    public static void closeDrawer(DrawerLayout drawerlayout) {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickProfile(View view){
        recreate();
    }

    public void ClickHome(View view){
        MyProfile.redirectActivity(this, Home.class);
    }

    public void ClickBrowser(View view){
        redirectActivity(this, BrowseBooks.class);
    }

    public void ClickLibrary(View view){
        redirectActivity(this, YourLibrary.class);
    }

    public void ClickLogout(View view){
        logout(this);
    }

    public static void logout(final Activity activity) {
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 activity.finishAffinity();
                 System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.putExtra("username", username);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void ClickAdd(View view){
        Intent i = new Intent(MyProfile.this, AddBook.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerlayout);
    }
}