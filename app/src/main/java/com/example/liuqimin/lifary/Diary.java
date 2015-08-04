package com.example.liuqimin.lifary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Calendar;

/**
 * Created by liuqimin on 15-07-07.
 */
public class Diary {

    private int _id;
    private String date;
    private String text;
    private float latitude, longitude;
    private int share;
    //private byte[]img;
    private String image;
    //private byte[] sound;
    private String sound;

    public Diary(){
        Calendar c= Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date = month + "-" + day + "-" + year + "  " +
                hour + ":" + minute + ":" + seconds;

        Log.d("Lifary", "date ===== " + date);

        //img = null;
        image = "";
        //sound = null;
        sound = "";
        text = "";
        latitude = 0;
        longitude = 0;
        share = 0;

    }
    public void addContents(String contents){
        text = contents;
    }

    public void addLocation(float lat, float lon){
        latitude = lat;
        longitude = lon;
    }
    public void addImage(Bitmap bmp){

        byte [] img = null;
        Log.d("Lifary", "load size");
        int size = bmp.getByteCount();
        Log.d("Lifary", "bmp size === " + size);
        ByteBuffer b = ByteBuffer.allocate(size);
        Log.d("Lifary", "create ByteBuffer");
        bmp.copyPixelsToBuffer(b);
        Log.d("Lifary", "copyPixelsTobuffer");
        img = new byte[size];
        Log.d("Lifary", "allocate img");
        img = b.array();
        Log.d("Lifary", "copy byte b to img , img.size = " + img.length);
        image = Base64.encodeToString(img, Base64.DEFAULT);
        Log.d("Lifary", "image: " + image);
    }
    public void addSound(byte[] audioByte){
        byte [] sound_temp = null;
        sound_temp= audioByte;
        Log.d("Lifary", "sound.size = " + sound_temp.length);
        sound = Base64.encodeToString(sound_temp, Base64.DEFAULT);
    }
    public void setImageByByte(byte[] imgByte){
        byte []img =null;
        img = imgByte;
        image = Base64.encodeToString(img, Base64.DEFAULT);
    }
    public void setAudioByte(byte[] audioByte){
        byte []sound_temp = null;
        sound_temp= audioByte;
        sound = Base64.encodeToString(sound_temp, Base64.DEFAULT);
    }

    public void setShare(int s){
        share = s;
    }
    public void setDate(String d){date = d;}

    public void setID(int id){_id = id;}

    public byte[] getImg(){
        byte []img= null;
        img = Base64.decode(image,Base64.DEFAULT);
        Log.d("img", "if right: getImg(): " + img.length);
        return img;
    }
    public byte[] getAudio(){
        byte []sound_temp= null;
        sound_temp = Base64.decode(sound,Base64.DEFAULT);
        return sound_temp;
    }
    public Bitmap getImgBitmap(){
        byte []img= null;
        img = Base64.decode(image,Base64.DEFAULT);
        Log.d("img", "if right: getImg(): " + img.length);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length, options);
        return bitmap;
    }
    public String getContent(){return text;}

    public int getID(){ return _id;}
    public String getDate(){return date;}
    public String getSound(){return sound;}
    public String getText(){return text;}
    public String getImage(){return image;}
    public float getLatitude(){return latitude;}
    public float getLongitude(){return longitude;}
    public int getShare(){return share;}

}
