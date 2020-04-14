package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.health.SystemHealthManager;
import android.sax.EndElementListener;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Timer extends SuperTimer implements Runnable
{
    private final static String LOG_TAG = "Timer";
    private ITimerState running;
    private ITimerState stopped;
    private ITimerState currentState;
    private List<IObserver> observers;
    private volatile boolean timeRunningBoolean = true;
    private long startTime = 0;
    private long currentTime = 0;
    private long endTime = 0;
    private boolean eggDone = false;
    
    public Timer()
    {
        this.running = new Running(this);
        this.stopped = new Stopped(this);
        
        currentState = stopped;
        observers = new ArrayList<>();
    }
    
    void setCurrentState(ITimerState newITimerState)
    {
        currentState = newITimerState;
    }
    
    ITimerState getRunning()
    {
        return running;
    }
    
    ITimerState getStopped()
    {
        return stopped;
    }
    
    @Override
    public void startTimer()
    {
        currentState.startTimer();
    }
    
    void startTimerThread()
    {
        new Thread(this).start();
    }
    
    void stopTimerThread()
    {
        Log.d(LOG_TAG, "stopTimerThread - called: ");
        currentState = getStopped();
        currentTime = 0;
        notifyObservers();
    }
    
    @Override
    public void set(long time)
    {
        Log.d(LOG_TAG, "set - time: " + time);
        currentState.setTimer(time);
    }
    
    @Override
    public void stopTimer()
    {
        Log.d(LOG_TAG, "stopTimer - called: ");
        this.currentState.stopTimer();
    }
    
    void setStartTime(long startTime)
    {
        this.startTime = startTime;
        this.currentTime = startTime;
        eggDone = false;
        notifyObservers();
    }
    
    public long getStartTime()
    {
        return startTime;
    }
    
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
            observer.onChange(this.currentTime);
            observer.eggDone(eggDone);
        }
    }
    
    @Override
    public void run()
    {
        Log.d(LOG_TAG, "run - called: ");
        endTime = System.currentTimeMillis() + startTime;
        startTime = System.currentTimeMillis();
        Log.d(LOG_TAG, "run - StartTime: " + startTime);
        Log.d(LOG_TAG, "run - EndTime: " + endTime);
//        currentTime = currentTime + System.currentTimeMillis();
        
        while (System.currentTimeMillis() - currentTime < endTime)
        {
            if (!currentState.equals(getStopped()))
            {
                try
                {
                    currentTime = updateCurrentTime();
                    notifyObservers();
                    Log.d(LOG_TAG, "run - currentTime: " + currentTime);
                    Thread.sleep(250);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            } else
            {
                break;
            }
        }
        if (!currentState.equals(getStopped()))
        {
            currentState = getStopped();
            eggDone = true;
        } else
        {
            eggDone = false;
        }
        startTime = 0;
        notifyObservers();
    }
    
    private long updateCurrentTime()
    {
        return endTime - System.currentTimeMillis();
    }
}
