package com.example.valuaomregner;

import android.os.Handler;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class exchangeRatesApi implements ICurrencyDao, IObservable
{
    Rate rate = null;
    Currency currencyy = null;
    
    
    private List<IObserver> observers = new ArrayList<>();
    
    @Override
    public void getRates(final String currency)
    {
        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "https://api.exchangeratesapi.io/latest";
        String url = "https://api.exchangeratesapi.io/latest?base=" + currency;
        Log.d(TAG, "getRates: url " + url);
        client.get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                final Handler handler = new Handler();
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Log.i(TAG, "deleteLogItem, onSuccess message");
                    }
                });
                
                
                if (null == responseBody)
                    return;
                
                String jsonString = TextHttpResponseHandler.getResponseString(responseBody, getCharset());
                
                Log.i(TAG, "deleteLogItem, onSuccess handler. jsonString: " + jsonString);
                
                if (jsonString != null)
                {
                    
                    final JSONObject json = getJsonObject(jsonString);
                    int error = json.optInt("errorCode");
                    String message = json.optString("message");
                    
                    if (error == 0)
                    {
                        Log.i(TAG, "message" + message);
                        
                        rate = new Rate();
                        currencyy  = new Currency();
                        
                        rate.fromJSON(jsonString);
                        Log.d(TAG, "onSuccess: header" + headers);
                        notifyObservers();
                    }
                }
                
                
            }
            
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                System.out.println("Failured to get rates, error code:" + statusCode);
            }
        });
    }
    
    @Override
    public void getHistory(String currency, Date startDate, Date endDate)
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
            observer.onRateChange(this.rate);
        }
    }
    
    public JSONObject getJsonObject(String jsonString)
    {
        Object result = null;
        
        if (jsonString != null)
        {
            jsonString = jsonString.trim();
            if (jsonString.startsWith("{") || jsonString.startsWith("["))
            {
                try
                {
                    result = new JSONTokener(jsonString).nextValue();
                } catch (JSONException e)
                {
                    Log.w(TAG, "getJsonObject, oh noes " + e.toString());
                }
            }
        }
        JSONObject json = new JSONObject();
        if (result == null)
        {
            result = jsonString;
        } else
        {
            json = (JSONObject) result;
        }
        
        return json;
    }
}
