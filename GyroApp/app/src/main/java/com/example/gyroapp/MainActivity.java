package com.example.gyroapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.renderscript.Sampler;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

public class MainActivity extends AppCompatActivity
{
    private SensorManager sensorManager;
    private Sensor gyroSensor;
    private TextView text1, text2, text3;
    private SensorEventListener gyroScopeEventListener;
    private ImageView ball;
    private float previousRotate = 0.0f;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ball = findViewById(R.id.ball);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        
        text1 = findViewById(R.id.sensor_text_1);
        text2 = findViewById(R.id.sensor_text_2);
        text3 = findViewById(R.id.sensor_text_3);
        
        
        gyroScopeEventListener = new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                
                text1.setText(String.valueOf(event.values[0]));
                text2.setText(String.valueOf(event.values[1]));
                text3.setText(String.valueOf(event.values[2]));
                
                
                if (event.values[0] > previousRotate + 0.05f || event.values[0] < previousRotate - 0.05f)
                {
                    AnimationSet animSet = new AnimationSet(true);
                    animSet.setInterpolator(new DecelerateInterpolator());
                    animSet.setFillAfter(true);
                    animSet.setFillEnabled(true);
                    
                    
                    final RotateAnimation animRotate = new RotateAnimation(previousRotate, event.values[0] * 18,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    
                    animRotate.setDuration(1);
                    animRotate.setFillAfter(true);
                    animSet.addAnimation(animRotate);
                    
                    ball.startAnimation(animSet);
                    previousRotate = event.values[0] * 18;
                }
                
            }
            
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {
            
            }
        };
        
        
    }
    
    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        sensorManager.registerListener(gyroScopeEventListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    @Override
    protected void onPause()
    {
        
        super.onPause();
        sensorManager.unregisterListener(gyroScopeEventListener);
    }
}