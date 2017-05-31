package com.example.dawid.flickr_wallpapers;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.inputmethodservice.Keyboard;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static ProgressBar progressBar;
    public static int height;
    public static int width;
    public static EditText editText;
    BottomNavigationView mBottomNavigation;
    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        editText=(EditText) findViewById(R.id.editText);
        mBottomNavigation=(BottomNavigationView)findViewById(R.id.bottomNavigation);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x/2;
        height = size.y/3;

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if(isOnline()){
            ShowRecent();
            progressBar.setVisibility(View.VISIBLE);
        }else Toast.makeText(this,R.string.no_internet,Toast.LENGTH_SHORT).show();

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager=getFragmentManager();
                Fragment fragment=null;
                int id=item.getItemId();
                if(isOnline()) {
                    switch (id) {
                        case R.id.recent:
                            ShowRecent();
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case R.id.popular:
                            fragment = new PopularFragment();
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case R.id.favorite:
                            fragment=new FavoriteFragment();
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                    }
                    if(fragment!=null) {
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    }
                }
                else Toast.makeText(getApplicationContext(),R.string.no_internet,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode== KeyEvent.KEYCODE_ENTER){
                    category=editText.getText().toString();
                        if(!category.isEmpty()) {
                            category=category.replaceAll(" ","+");
                            FragmentManager fragmentManager = getFragmentManager();
                            Fragment fragment = new SearchFragment();
                            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                            progressBar.setVisibility(View.VISIBLE);
                            mBottomNavigation.getMenu().findItem(R.id.recent).setChecked(true);

                        }
                        else Toast.makeText(getApplicationContext(),R.string.empty,Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private boolean isOnline(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null&&networkInfo.isConnected());
    }
    private void ShowRecent(){
        FragmentManager fragmentManager=getFragmentManager();
        Fragment fragment=new RecentFragment();
        fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();
    }
}
