package com.bsav157.lastfm.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bsav157.lastfm.interfaces.IModel;
import com.bsav157.lastfm.interfaces.IPresenter;
import com.bsav157.lastfm.model.data.Artist;
import com.bsav157.lastfm.model.data.LastFM;
import com.bsav157.lastfm.model.io.ApiAdapter;
import com.bsav157.lastfm.view.activities.ListActivity;

import java.util.ArrayList;

import static com.bsav157.lastfm.model.utils.Defines.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interactor implements IModel, Callback<LastFM> {

    private IPresenter presenter;
    private Context myCtx;

    public Interactor(IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeApiQuery(String country, Context ctx) {
        myCtx = ctx;
        Call<LastFM> call = ApiAdapter.getApiService().getLastFM(METHOD, country, KEY, FORMAT);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LastFM> call, Response<LastFM> response) {
        if(response.isSuccessful()){
            LastFM lastFM = response.body();
            if(lastFM != null){
                Intent in = new Intent(myCtx, ListActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("LastFM", lastFM);
                in.putExtras(bundle);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myCtx.startActivity(in);
            }
        }
    }

    @Override
    public void onFailure(Call<LastFM> call, Throwable t) {
        //TODO
    }

    @Override
    public void processData(LastFM lastFM) {
        if (lastFM != null){
            presenter.showData((ArrayList<Artist>) lastFM.getTopArtists().getArtist());
        }

    }
}
