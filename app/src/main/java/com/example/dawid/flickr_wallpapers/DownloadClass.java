package com.example.dawid.flickr_wallpapers;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dawid on 29.05.2017.
 */

public class DownloadClass extends AsyncTask<String,String,Long> {
    Context mContext;
    String mPath;
    ArrayList<PhotoClass> photosList=new ArrayList<PhotoClass>();
    public DownloadClass(Context context,String path) {
        super();
        this.mContext=context;
        this.mPath=path;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if(aLong==1l) {
            PhotoAdapter adapter = new PhotoAdapter(mContext, photosList);
            MainActivity.recyclerView.setAdapter(adapter);
        }
        MainActivity.progressBar.setVisibility(View.GONE);
        MainActivity.editText.setText("");
    }

    @Override
    protected Long doInBackground(String... params) {
        HttpURLConnection connection=null;
        try{
            URL address=new URL(mPath);
            connection=(HttpURLConnection)address.openConnection();
            connection.connect();
            int responseCode=connection.getResponseCode();
            if(responseCode==200) {
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String lines;
                while ((lines = reader.readLine()) != null) {
                    sb.append(lines);
                }
                String photoData = sb.toString();
                photosList = PhotoClass.makephotoArray(photoData);
                return 1l;
            }else return 0l;
        }catch (MalformedURLException e){
            e.printStackTrace();
            return 0l;
        }catch (IOException e){
            e.printStackTrace();
            return 0l;
        }catch (JSONException e){
            e.printStackTrace();
            return 0l;
        }
    }
}
