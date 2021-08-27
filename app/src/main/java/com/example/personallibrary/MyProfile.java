package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyProfile extends AppCompatActivity {

    private static String username = "";
    DrawerLayout drawerlayout;
    private EditText countdownTimerEditTxt;
    private Button countdownTimerStartBtn;
    private TextView countdownTimerText;
    TextView user, books, hours, pages, currentNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user = findViewById(R.id.username_prof);
        books = findViewById(R.id.booksperyear);
        hours = findViewById(R.id.hoursperweek);
        pages = findViewById(R.id.pagesperday);
        currentNo = findViewById(R.id.booksinlib);
        drawerlayout = findViewById(R.id.drawer_layout);

        DatabaseHelper db = new DatabaseHelper(this);

        Intent i = getIntent();
        username = i.getStringExtra("username");

        user.setText(username);

        Goal goal = db.getGoal(username);

        books.setText(String.valueOf(goal.getBooks()));
        hours.setText(String.valueOf(goal.getHours()));
        pages.setText(String.valueOf(goal.getPages()));

        List<BookA> books = db.getBooks();
        currentNo.setText(String.valueOf(books.size()));

        countdownTimerEditTxt = (EditText) findViewById(R.id.countdown_edit_txt);
        countdownTimerStartBtn = (Button) findViewById(R.id.countdown_btn);
        countdownTimerText = (TextView) findViewById(R.id.countdown_timer_txt);
        countdownTimerText.setTypeface(Typeface.DEFAULT_BOLD);
        countdownTimerStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = countdownTimerEditTxt.getText().toString();
                if(!val.equals("")) {
                    try {
                        long timerSetter = Long.parseLong(val);
                        startService(new Intent(MyProfile.this,
                                CountdownTimerService.class).putExtra("setTimer",
                                TimeUnit.MINUTES.toMillis(timerSetter)));
                        countdownTimerEditTxt.setEnabled(false);
                        countdownTimerStartBtn.setEnabled(false);
                    }
                    catch(Exception e) {
                        Toast.makeText(MyProfile.this,
                                "Something went wrong please try again! Check your input",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MyProfile.this,
                            "Please enter valid time in minute", Toast.LENGTH_LONG).show();
                }
            }
        });
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
        redirectActivity(this, Home.class);

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

    public static void redirectActivity(Activity activity, Object aClass) {
        Intent intent = new Intent(activity, (Class<?>) aClass);
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
        unregisterReceiver(countdownTimerReceiver);
    }

    private BroadcastReceiver countdownTimerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateCountdownTimer(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(countdownTimerReceiver, new IntentFilter(AppComponent.Constant.COUNTDOWN_TIMER_BROADCAST_RECEIVER));
    }



    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(countdownTimerReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MyProfile.this, CountdownTimerService.class));
    }

    private void updateCountdownTimer(Intent intent) {
        if(intent.getExtras() != null) {
            try {
                String timerValue = intent.getStringExtra("countdownTimer");
                if(timerValue != null) {
                    countdownTimerText.setText(timerValue);
                }
                else {
                    countdownTimerText.setText("Something went wrong please try again!");
                }
            }
            catch(Exception e) {
                Toast.makeText(MyProfile.this, "Something went wrong please try again!", Toast.LENGTH_LONG).show();
            }
            try {
                if(countdownTimerText.getText().toString().equals("Countdown Finished")) {
                    countdownTimerEditTxt.setText("");
                    countdownTimerEditTxt.setEnabled(true);
                    countdownTimerStartBtn.setEnabled(true);
                }
            }
            catch(Exception e) {
                Toast.makeText(MyProfile.this, "Something went wrong please try again!", Toast.LENGTH_LONG).show();
            }
        }
    }
}