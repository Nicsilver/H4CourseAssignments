package com.example.eggtimer;

import android.util.Log;

public class Presenter implements IEggTimerContract.presenter, IObserver
{
    private final static String LOG_TAG = "presenter";
    private SuperTimer timer;
    private IEggTimerContract.view view;
    
    public Presenter(IEggTimerContract.view view)
    {
        timer = TimerFactory.getInstance().createTimer();
        timer.addObserver(this);
        this.view = view;
    }
    
    @Override
    public void setTime(long time)
    {
        timer.set(time);
    }
    
    @Override
    public void stopTimer()
    {
        timer.stopTimer();
    }
    
    @Override
    public void startTimer()
    {
        timer.startTimer();
    }
    
    @Override
    public void onChange(long time)
    {
        Log.d(LOG_TAG, "onChange - called: ");
        view.setTime(time);
        
    }
    
    @Override
    public void eggDone(boolean finished)
    {
        if (finished)
        {
            view.eggDone();
        }
    }
}
