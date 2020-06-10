package com.example.valuaomregner;

import java.util.Date;

public interface ICurrencyDao extends IObservable
{
    void getRates(String currency);
    
    void getHistory(String currency, Date startDate, Date endDate);

    
    
}
