package com.example.valuaomregner;

import java.util.Dictionary;

public class Currency
{
    String base;
    String date;
    Dictionary<String, Double> rates;
    
    public String getBase()
    {
        return base;
    }
    
    public void setBase(String base)
    {
        this.base = base;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public Dictionary<String, Double> getRates()
    {
        return rates;
    }
    
    public void setRates(Dictionary<String, Double> rates)
    {
        this.rates = rates;
    }
}
