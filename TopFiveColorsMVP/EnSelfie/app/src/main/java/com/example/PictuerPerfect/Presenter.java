package com.example.PictuerPerfect;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Observer;

public class Presenter implements Contract.presenter, IObserver
{
    PicturePerfect picturePerfect;
    Contract.view view;
    private final static String LOG_TAG = "presenter";
    
    public Presenter(Contract.view view)
    {
        this.view = view;
        this.picturePerfect = new PicturePerfect();
        this.picturePerfect.addObserver(this);
    }
    
    @Override
    public void calculateColors(Bitmap bitmap)
    {
        picturePerfect.calculateColors(bitmap);
        
    }
    
    @Override
    public void onChange(int[] dominantColors)
    {
        for (int i = 0; i < dominantColors.length; i++)
        {
            Log.d(LOG_TAG, "onChange - : dominant colors" + dominantColors[i]);
            this.view.setColors(dominantColors);
        }
    }
}
