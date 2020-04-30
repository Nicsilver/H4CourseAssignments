package com.example.PictuerPerfect;

import android.graphics.Bitmap;

public interface Contract
{
    interface view
    {
        void setColors(int[] colors);
    }
    
    interface presenter
    {
        void calculateColors(Bitmap bitmap);
    }
}
