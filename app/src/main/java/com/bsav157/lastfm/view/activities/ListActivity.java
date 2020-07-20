package com.bsav157.lastfm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
    private GridLayoutManager grid;
    private ArtistAdapter adapter;
    private TextView country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        country = findViewById(R.id.country);

        Presenter presenter = new Presenter(this);
        LastFM lastFM = (LastFM) getIntent().getSerializableExtra("LastFM");
        if(lastFM != null){
            presenter.processData(lastFM);
            String text = "TOP 50 " + lastFM.getTopArtists().getAttr().getCountry();
            country.setText(text);
        }

    }

    @Override
    public void showData(ArrayList<Artist> artists) {
        recycler = findViewById(R.id.list_artists);
        grid = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(grid);
        adapter = new ArtistAdapter(artists);
        recycler.setAdapter(adapter);
    }

}