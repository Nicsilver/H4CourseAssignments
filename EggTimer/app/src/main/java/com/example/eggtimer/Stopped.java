package com.example.eggtimer;

import android.util.Log;

class Stopped implements ITimerState
{
    private final static String LOG_TAG = "Stopped";
    private com.example.eggtimer.Timer timer;
    
    Stopped(Timer timer)
    {
        this.timer = timer;
    }
    
    @Override
    public void startTimer()
    {
        Log.d(LOG_TAG, "startTimer - called: ");
//        timer.run();
        timer.startTimerThread();
        timer.setCurrentState(timer.getRunning());
    }
    
    @Override
    public void setTimer(long time)
    {
        Log.d(LOG_TAG, "setTimer - time: " + time);
        timer.setStartTime(time);
        timer.notifyObservers();
    }
    
    @Override
    public void stopTimer()
    {
    
    }
    
}
