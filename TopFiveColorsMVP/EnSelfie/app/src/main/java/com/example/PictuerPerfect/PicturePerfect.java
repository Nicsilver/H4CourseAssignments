package com.example.PictuerPerfect;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PicturePerfect implements IObservable
{
    private final static String LOG_TAG = "PictuerPerfect";
    private List<IObserver> observers = new ArrayList<>();
    Map<Integer, Integer> colors;
    private int[] mostDominant = new int[5];
    private int colorToCompareWithInt;
    
    public void calculateColors(Bitmap bitmap)
    {
        colors = new TreeMap<>();
        Palette p = Palette.from(bitmap).generate();
//        Bitmap[][] bitmaps = splitBitmap(bitmap, 2, 2);
//        Palette.generate(bitmap, 2);

//        bitmap.setHeight(bitmap.getHeight() / 4);
//        bitmap.setWidth(bitmap.getWidth() / 4);
//        int iterations = bitmap.getHeight() + bitmap.getWidth();
//        for (int i = 0; i < iterations; i++)
//        {
        
        for (int i = 0; i < bitmap.getWidth(); i++)
        {
            for (int j = 0; j < bitmap.getHeight(); j++)
            {
                int color = bitmap.getPixel(i, j);
                
                if (colors.containsKey(color))
                {
                    colors.put(color, colors.get(color) + 1);
                } else
                {
                    colors.put(color, 1);
                }
                
                
            }
        }
        List<Map.Entry<Integer, Integer>> result = new ArrayList<>(colors.entrySet());
        
        result.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        
        colorToCompareWithInt = result.get(0).getKey();
        
        for (int i = 0; i < 5; i++)
        {
//            mostDominant[i] = result.get(i).getKey();
//            findDominantColors();
            int j = 0;
            while (true)
            {
                Color dominantColorToCompareWith = Color.valueOf(colorToCompareWithInt);
                Color colorToCompareWith = Color.valueOf(result.get(j).getKey());
                double distance = Math.sqrt((dominantColorToCompareWith.red() - colorToCompareWith.red()) * (dominantColorToCompareWith.green() - colorToCompareWith.green()) * (dominantColorToCompareWith.blue() - colorToCompareWith.blue()));
                
                Log.d(LOG_TAG, "calculateColors - distance : for j " + j + " --- distance: " + distance);
                if (distance > 0.04f)
                {
                    mostDominant[i] = result.get(j).getKey();
                    colorToCompareWithInt = result.get(j).getKey();
                    j++;
                    break;
                } else
                {
                    j++;
                }
                
                
            }
            
        }
        notifyObservers();
    }

//    private void findDominantColors()
//    {
//        Color color = Color.valueOf(colorToFind);
//
//
//
//        color.red();
//
//
//    }
    
    @Override
    public void addObserver(IObserver iObserver)
    {
        observers.add(iObserver);
    }
    
    @Override
    public void notifyObservers()
    {
        for (IObserver observer : observers)
        {
            Log.d(LOG_TAG, "notifyObservers - called: ");
            observer.onChange(this.mostDominant);
        }
    }
    
    public Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount)
    {
        // Allocate a two dimensional array to hold the individual images.
        Bitmap[][] bitmaps = new Bitmap[xCount][yCount];
        int width, height;
        // Divide the original bitmap width by the desired vertical column count
        width = bitmap.getWidth() / xCount;
        // Divide the original bitmap height by the desired horizontal row count
        height = bitmap.getHeight() / yCount;
        // Loop the array and create bitmaps for each coordinate
        for (int x = 0; x < xCount; ++x)
        {
            for (int y = 0; y < yCount; ++y)
            {
                // Create the sliced bitmap
                bitmaps[x][y] = Bitmap.createBitmap(bitmap, x * width, y * height, width, height);
            }
        }
        // Return the array
        return bitmaps;
    }
}
