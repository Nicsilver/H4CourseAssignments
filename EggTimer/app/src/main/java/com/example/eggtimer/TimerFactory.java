package com.example.eggtimer;

public class TimerFactory implements ITimerFactory
{
    private static TimerFactory timerFactory = null;
    
    //Cant use an interface............................
    public static ITimerFactory getInstance()
    {
        if (timerFactory == null)
            timerFactory = new TimerFactory();

        return timerFactory;
    }
    
    
    @Override
    public Timer createTimer()
    {
        return new Timer();
    }
}
