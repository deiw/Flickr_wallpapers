package com.example.dawid.flickr_wallpapers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dawid on 29.05.2017.
 */

public class PhotoClass extends Object {
    String id;
    String server;
    String secret;
    String farm;
    String title;
    String small_image;
    String big_image;
    public PhotoClass(JSONObject photo) throws JSONException {
        this.id= photo.optString("id");
        this.server=photo.optString("server");
        this.title=(String)photo.optString("title");
        this.secret=photo.optString("secret");
        this.farm=photo.optString("farm");
        this.small_image="http://farm"+this.farm + ".staticflickr.com/"+this.server+"/"+ this.id+"_"+this.secret +"_n.jpg";
        this.big_image="http://farm"+this.farm + ".staticflickr.com/"+this.server+"/"+ this.id+"_"+this.secret +"_c.jpg";
    }
    public static ArrayList<PhotoClass> makephotoArray(String photoData) throws JSONException{
        ArrayList<PhotoClass> photoList=new ArrayList<PhotoClass>();
        JSONObject data=new JSONObject(photoData);
        JSONObject photos=data.optJSONObject("photos");
        JSONArray photoArray=photos.optJSONArray("photo");
        for(int i=0;i<photoArray.length();i++){
            JSONObject photo=(JSONObject)photoArray.get(i);
            PhotoClass current=new PhotoClass(photo);
            photoList.add(current);
        }
        return photoList;
    }
}
