package com.example.dawid.flickr_wallpapers;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;


public class FullscreenActivity extends AppCompatActivity {
    Button buttonWallpaper;
ImageView fullscreenImage;
    String title;
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        fullscreenImage=(ImageView)findViewById(R.id.fullImage);
        buttonWallpaper=(Button)findViewById(R.id.buttonWallpaper);

        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        imageURL=intent.getStringExtra("image");

        Picasso.with(this).load(imageURL).into(fullscreenImage);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap wallpaper=((BitmapDrawable)fullscreenImage.getDrawable()).getBitmap();
                WallpaperManager mWallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                try {
                     mWallpaperManager.setBitmap(wallpaper);
                    Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.error,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
