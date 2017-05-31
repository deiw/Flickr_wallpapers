package com.example.dawid.flickr_wallpapers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Dawid on 29.05.2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context mCtx;
    ArrayList<PhotoClass> mList;
    public PhotoAdapter(Context context, ArrayList<PhotoClass> list) {
        super();
        this.mCtx=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mCtx).load(mList.get(position).small_image).resize(MainActivity.width,MainActivity.height)
                .centerCrop().into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation=AnimationUtils.loadAnimation(mCtx,R.anim.click_anim);
                holder.imageView.startAnimation(animation);
                PhotoClass photo=mList.get(position);
                Intent intent=new Intent(mCtx,FullscreenActivity.class);
                intent.putExtra("title",photo.title);
                intent.putExtra("image",photo.big_image);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
