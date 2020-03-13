package com.example.android.hellosharedprefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Dictionary;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int mCount;
    private int mColor;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();
        mCount = intent.getIntExtra("mCount", 0);
        mColor = intent.getIntExtra("mColor", 0);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void reset(View view) {
        // Reset count
        mCount = 0;

        // Reset color
        mColor = ContextCompat.getColor(this,
                R.color.default_background);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }


    public void save(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                mColor = ContextCompat.getColor(this, R.color.black);
                break;
            case 1:
                mColor = ContextCompat.getColor(this, R.color.red_background);
                break;
            case 2:
                mColor = ContextCompat.getColor(this, R.color.blue_background);
                break;
            case 3:
                mColor = ContextCompat.getColor(this, R.color.green_background);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
