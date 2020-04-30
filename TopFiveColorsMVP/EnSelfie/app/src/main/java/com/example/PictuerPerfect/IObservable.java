package com.example.PictuerPerfect;

public interface IObservable
{
    void addObserver(IObserver iObserver);
    void notifyObservers();
}
