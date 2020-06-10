package com.example.valuaomregner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CurrencyContract.view
{
    CurrencyContract.presenter presenter;
    
    TextView currencyText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        presenter = new Presenter(this);
//        presenter.getRates("EUR", 1); //TODO TEST
        
        final Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        
        currencyText = findViewById(R.id.currency_text);
        currencyText.setText(spinner.getItemAtPosition(0).toString());
        
        currencyText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                spinner.performClick();
            }
        });
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinner.setSelection(position);
                currencyText.setText(parent.getItemAtPosition(position).toString());
            }
    
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
        
            }
        });
        
    }
    
    @Override
    public void setRates(Rate Rate)
    {
        Log.d("TAG", "setRates: called" + Rate);
    }
    
    @Override
    public void setHistory()
    {
    
    }
}