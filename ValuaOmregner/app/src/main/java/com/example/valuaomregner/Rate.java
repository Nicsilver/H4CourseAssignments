package com.example.valuaomregner;

import android.util.Log;

import com.google.gson.Gson;

public class Rate
{
    private String base;
    private String date;
    private Rates rates;
    
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
    
    public Rates getRates()
    {
        return rates;
    }
    
    public void setRates(Rates rates)
    {
        this.rates = rates;
    }
    
    public String toJSON()
    {
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
    
    public void fromJSON(String jsonString)
    {
        Log.d("TAG", "fromJSON: string" + jsonString);
        Gson gson = new Gson();
        Rate rate = gson.fromJson(jsonString, Rate.class);

//        Rates rates = gson.fromJson(jsonString, new TypeToken<Rates>(){}.getType());
//        Map<String, Double> map = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Integer>>() {}.getType());
        this.base = rate.getBase();
        this.date = rate.getDate();
        this.rates = rate.rates;
    }
    
    
}
