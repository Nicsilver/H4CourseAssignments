package com.example.valuaomregner;

import java.util.Date;

public class Presenter implements CurrencyContract.presenter, IObserver
{
    CurrencyContract.view view;
    Calculator calculator;
    
    public Presenter(CurrencyContract.view view)
    {
        this.view = view;
        this.calculator = new Calculator();
        this.calculator.addObserver(this);
        getRates("EUR", 1); //TODO TEST
    }
    
    @Override
    public void getRates(String currency, double amount)
    {
        this.calculator.getRates(currency, amount);
    }
    
    @Override
    public void getHistory(String currency, Date startDate, Date endDate)
    {
        
    }
    
    @Override
    public void onRateChange(Rate Rate)
    {
        this.view.setRates(Rate);
    }
}
