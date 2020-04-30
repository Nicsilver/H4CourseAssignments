package com.example.PictuerPerfect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.view
{
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Contract.presenter presenter;
    List<TextView> colorTexts = new ArrayList<>();
    List<ImageView> colorBoxes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        presenter = new Presenter(this);
        
        colorTexts.add(findViewById(R.id.color_1));
        colorTexts.add(findViewById(R.id.color_2));
        colorTexts.add(findViewById(R.id.color_3));
        colorTexts.add(findViewById(R.id.color_4));
        colorTexts.add(findViewById(R.id.color_5));
    
        colorBoxes.add(findViewById(R.id.color_box_1));
        colorBoxes.add(findViewById(R.id.color_box_2));
        colorBoxes.add(findViewById(R.id.color_box_3));
        colorBoxes.add(findViewById(R.id.color_box_4));
        colorBoxes.add(findViewById(R.id.color_box_5));
        
        
        
        this.imageView = findViewById(R.id.selfie_imageview);
        findViewById(R.id.takeselfie_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dispatchTakePictureIntent();
            }
        });
        
    }
    
    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            presenter.calculateColors(imageBitmap);
        }
    }
    
    @Override
    public void setColors(int[] colors)
    {
        for (int i = 0; i < colors.length; i++)
        {
            String hexColor = String.format("#%06X", (0xFFFFFF & colors[i]));
            
            colorTexts.get(i).setText(hexColor);
            colorBoxes.get(i).setBackgroundColor(colors[i]);
        }
    }
}
