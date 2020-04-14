package com.example.eggtimer;

public interface IObservable
{
    void addObserver(IObserver iObserver);
    void notifyObservers();
}
