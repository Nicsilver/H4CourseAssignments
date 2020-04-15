package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class EggTimerActivity extends AppCompatActivity implements IEggTimerContract.view, View.OnClickListener
{
    private final static String LOG_TAG = "EggTimerActivity";
    private IEggTimerContract.presenter presenter;
    private TextView displayTime;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        presenter = PresenterFactory.getInstance().createPresenter(this);
        
        findViewById(R.id.egg_soft).setOnClickListener(this);
        findViewById(R.id.egg_medium).setOnClickListener(this);
        findViewById(R.id.egg_hard).setOnClickListener(this);
        displayTime = findViewById(R.id.display_time);
        findViewById(R.id.start_button).setOnClickListener(this);
        findViewById(R.id.stop_button).setOnClickListener(this);
        presenter.setTime(Long.parseLong(getString(R.string.soft)));
        
    }
    
    @Override
    public void setTime(long time)
    {
        
        Log.d(LOG_TAG, "setTime - called: " + time);
//        long minutes = (time % 3600) / 60;
//        long seconds = time % 60;
        
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;

//        String timeString = String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds);
        final String timeString = String.format(Locale.ENGLISH, "%02d:%02d", minute, second);
        Log.d(LOG_TAG, "setTime - timeString: " + timeString);
        this.runOnUiThread(new Runnable()
        {
            public void run()
            {
                displayTime.setText(timeString);
            }
        });
    }
    
    @Override
    public void stopTimer()
    {
        Log.d(LOG_TAG, "stopTimer - called: ");
        presenter.stopTimer();
    }
    
    @Override
    public void startTimer()
    {
        presenter.startTimer();
    }
    
    @Override
    public void eggDone()
    {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.egg_done_sound);
        mp.start();
    }
    
    @Override
    public void onClick(View v)
    
    {
        switch (v.getId())
        {
            case R.id.egg_soft:
                presenter.setTime(Long.parseLong(getString(R.string.soft)));
                break;
            case R.id.egg_medium:
                presenter.setTime(Long.parseLong(getString(R.string.medium)));
                break;
            case R.id.egg_hard:
                presenter.setTime(Long.parseLong(getString(R.string.hard)));
                break;
            case R.id.start_button:
                startTimer();
                break;
            case R.id.stop_button:
                stopTimer();
                break;
            
        }
    }
}
