package com.example.valuaomregner;

public interface IObservable
{
    void addObserver(IObserver iObserver);
    void notifyObservers();
}
