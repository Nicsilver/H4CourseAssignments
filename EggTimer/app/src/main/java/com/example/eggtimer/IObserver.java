package com.example.eggtimer;

public interface IObserver
{
    void onChange(long time);
    void eggDone(boolean finished);
}
