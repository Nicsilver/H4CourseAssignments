package com.example.valuaomregner;

import java.util.Date;

public interface CurrencyContract
{
    interface presenter
    {
        void getRates(String currency, double amount);
        void getHistory(String currency, Date startDate, Date endDate);
    }
    
    interface view
    {
        void setRates(Rate Rate);
        void setHistory();
    }
}
