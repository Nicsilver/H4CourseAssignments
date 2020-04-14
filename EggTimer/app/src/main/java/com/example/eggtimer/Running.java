package com.example.eggtimer;

import android.util.Log;

class Running implements ITimerState
{
    private final static String LOG_TAG = "running";
    private Timer timer;
    
    public Running(Timer timer)
    {
        this.timer = timer;
    }
    
    @Override
    public void startTimer()
    {
        Log.d(LOG_TAG, "startTimer - called: ");
    }
    
    @Override
    public void setTimer(long time)
    {
        Log.d(LOG_TAG, "setTimer - doing nothing: ");
    }
    
    @Override
    public void stopTimer()
    {
        Log.d(LOG_TAG, "stopTimer - called: ");
        timer.stopTimerThread();
    }
    
}
