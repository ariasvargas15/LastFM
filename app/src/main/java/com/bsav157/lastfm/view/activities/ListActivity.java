package com.bsav157.lastfm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsav157.lastfm.R;
import com.bsav157.lastfm.interfaces.IView;
import com.bsav157.lastfm.model.data.Artist;
import com.bsav157.lastfm.model.data.LastFM;
import com.bsav157.lastfm.presenter.Presenter;
import com.bsav157.lastfm.view.adapters.ArtistAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements IView {

    private RecyclerView recycler;
    private TextView messageView;
    private TextView country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        country = findViewById(R.id.country);
        messageView = findViewById(R.id.message);
        recycler = findViewById(R.id.list_artists);

        getMessage();
        getCountry();
        getTopArtists();
    }

    @Override
    public void showData(ArrayList<Artist> artists) {
        GridLayoutManager grid = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(grid);
        ArtistAdapter adapter = new ArtistAdapter(artists);
        recycler.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        recycler.setVisibility(View.GONE);
        messageView.setVisibility(View.VISIBLE);
        messageView.setText(message);
    }

    private void getMessage(){
        boolean response = getIntent().getBooleanExtra("message", false);
        if(response){
            showMessage("THERE IS NO DATA FOR THIS COUNTRY, TRY WITH ANOTHER");
        }
    }

    private void getCountry(){
        String countryName = getIntent().getStringExtra("country");
        if (countryName != null){
            String text = "TOP 50 " + countryName;
            country.setText(text);
        }
    }

    private void getTopArtists(){
        LastFM lastFM = (LastFM) getIntent().getSerializableExtra("LastFM");
        if(lastFM != null){
            Presenter presenter = new Presenter(this);
            presenter.processData(lastFM);
        }
    }
}