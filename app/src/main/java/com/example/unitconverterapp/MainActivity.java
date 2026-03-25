package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerFrom, spinnerTo;
    EditText editTextValue;
    TextView textViewResult;
    Button buttonConvert;

    String[] units = {
            "USD", "AUD", "EUR", "JPY", "GBP",
            "mpg", "km/L", "Gallon(US)", "Liters", "Nautical Mile", "Kilometers",
            "Celsius", "Fahrenheit", "Kelvin"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        units);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();
        String valueStr = editTextValue.getText().toString();

        if (valueStr.isEmpty()) {
            textViewResult.setText("Please enter a value!");
            return;
        }

        if (fromUnit.equals(toUnit)) {
            textViewResult.setText("Source and destination units are same.");
            return;
        }

        double value = Double.parseDouble(valueStr);
        double result = 0;


        if (fromUnit.equals("USD") && toUnit.equals("AUD"))
            result = value * 1.55;

        else if (fromUnit.equals("USD") && toUnit.equals("EUR"))
            result = value * 0.92;


        else if (fromUnit.equals("USD") && toUnit.equals("JPY"))
            result = value * 148.50;


        else if (fromUnit.equals("USD") && toUnit.equals("GBP"))
            result = value * 0.78;

        else if(fromUnit.equals("mpg") && toUnit.equals("Kilometers"))
            result = value * 0.425;

        else if(fromUnit.equals("Gallon(US)") && toUnit.equals("Literes"))
            result = value * 3.785;

        else if(fromUnit.equals("Nautical Mile") && toUnit.equals("Kilometers"))
            result = value * 1.852;

        else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit"))
            result = (value + 32) / 1.8;

        else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius"))
            result = (value - 32) / 1.8;

        else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin"))
            result = value + 273.15;
        else if(fromUnit.equals("Kelvin") && toUnit.equals("Celsius"))
            result = value - 273.15;

        else {
            textViewResult.setText("Conversion not supported.");
            return;
        }

        textViewResult.setText("Result: " + result);
    }
}




