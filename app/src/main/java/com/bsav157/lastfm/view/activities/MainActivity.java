package com.bsav157.lastfm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bsav157.lastfm.R;
import com.bsav157.lastfm.interfaces.IView;
import com.bsav157.lastfm.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements IView {

    private TextView textV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.countries_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Presenter presenter = new Presenter(this);
        presenter.makeApiQuery();
    }

    @Override
    public void showData(String result) {

    }
}