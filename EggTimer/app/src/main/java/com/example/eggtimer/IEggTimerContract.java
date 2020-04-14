package com.example.eggtimer;

public interface IEggTimerContract
{
    interface view
    {
        void setTime(long time);
        void stopTimer();
        void startTimer();
        void eggDone();
    }
    
    interface presenter
    {
        void setTime(long time);
        void stopTimer();
        void startTimer();
    }
}
