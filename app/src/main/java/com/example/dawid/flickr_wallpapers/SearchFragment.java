package com.example.dawid.flickr_wallpapers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static android.R.attr.category;
import static android.R.attr.format;

/**
 * Created by Dawid on 29.05.2017.
 */

public class SearchFragment extends Fragment {

    RecyclerView.LayoutManager lm;
    public SearchFragment() {
        super();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String pathURL="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=f2d5af237aa93d59fea745ee42c6d410&text="+
                MainActivity.category+"&per_page=100&format=json&nojsoncallback=1";
        DownloadClass task=new DownloadClass(this.getActivity(),pathURL);
        task.execute();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.adapter_layout,container,false);
        MainActivity.recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView);
        lm=new GridLayoutManager(this.getActivity(),2);
        MainActivity.recyclerView.setLayoutManager(lm);
        return v;
    }
}
