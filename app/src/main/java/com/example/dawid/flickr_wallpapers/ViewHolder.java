package com.example.dawid.flickr_wallpapers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Dawid on 29.05.2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.imageView);
    }
}
