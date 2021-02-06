package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class BookActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
    String username = "";
    String title = "", authors = "", description = "", categories = "", publishDate = "", info = "", buy = "", preview = "", thumbnail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        drawerlayout = findViewById(R.id.drawer_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            buy = extras.getString("book_buy");
            preview = extras.getString("book_preview");
            thumbnail = extras.getString("book_thumbnail");
        }

        //CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        //collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvTitle = findViewById(R.id.aa_book_name);
        TextView tvAuthors = findViewById(R.id.aa_author);
        TextView tvDesc = findViewById(R.id.aa_description);
        TextView tvCatag = findViewById(R.id.aa_categorie);
        TextView tvPublishDate = findViewById(R.id.aa_publish_date);
        TextView tvInfo = findViewById(R.id.aa_info);
        TextView tvPreview = findViewById(R.id.aa_preview);

        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvTitle.setText(title);
        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvCatag.setText(categories);
        tvPublishDate.setText(publishDate);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);
            }
        });


        final String finalPreview = preview;
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalPreview));
                startActivity(i);

            }
        });

        // collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
    }

    public void ClickAdd(View view){
        Intent i = new Intent(BookActivity.this, AddBook.class);
        i.putExtra("username", username);
        i.putExtra("title", title);
        i.putExtra("authors", authors);
        startActivity(i);
    }


    public void ClickMenu(View view) {
        MyProfile.openDrawer(drawerlayout);
    }

    public void ClickLogo(View view) {
        MyProfile.closeDrawer(drawerlayout);
    }

    public void ClickBrowser(View view) {
        MyProfile.redirectActivity(this, BrowseBooks.class);
    }

    public void ClickLibrary(View view) {
        MyProfile.redirectActivity(this, YourLibrary.class);
    }

    public void ClickProfile(View view) {
        MyProfile.redirectActivity(this, MyProfile.class);
    }

    public void ClickHome(View view) {
        MyProfile.redirectActivity(this, Home.class);
    }

    public void CLickLogout(View view) {
        MyProfile.logout(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MyProfile.closeDrawer(drawerlayout);
    }
}