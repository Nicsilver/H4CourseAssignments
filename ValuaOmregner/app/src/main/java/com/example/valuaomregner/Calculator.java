package com.example.valuaomregner;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements ICalculator, IObserver, IObservable
{
    ICurrencyDao currencyDao;
    Rate Rate;
    private List<IObserver> observers = new ArrayList<>();
    
    public Calculator()
    {
        this.currencyDao = new exchangeRatesApi();
        this.currencyDao.addObserver(this);
    }
    
    
    @Override
    public void getRates(String currency, double amount)
    {
        currencyDao.getRates(currency);
    }
    
    @Override
    public void getHistory()
    {
    
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
            System.out.println("Notify observers");
            observer.onRateChange(this.Rate);
        }
    }
    
    @Override
    public void onRateChange(Rate Rate)
    {
        //TODO do some logic here
        
        for ()
        
        this.Rate = Rate;
        notifyObservers();
    }
}
