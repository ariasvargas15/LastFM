package com.bsav157.lastfm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bsav157.lastfm.R;
import com.bsav157.lastfm.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.countries_list);
        button = findViewById(R.id.search_button);

        button.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.search_button){
           String country = spinner.getSelectedItem().toString();
           makeQuery(country);
        }
    }

    private void makeQuery(String country){
        Presenter presenter = new Presenter();
        presenter.makeApiQuery(country, getApplicationContext());
    }
}