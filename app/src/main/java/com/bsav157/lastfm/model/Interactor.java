package com.bsav157.lastfm.model;

import com.bsav157.lastfm.interfaces.IModel;
import com.bsav157.lastfm.interfaces.IPresenter;
import com.bsav157.lastfm.model.data.LastFM;
import com.bsav157.lastfm.model.io.ApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interactor implements IModel, Callback<LastFM> {

    private IPresenter presenter;

    public Interactor(IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeApiQuery() {
        Call<LastFM> call = ApiAdapter.getApiService().getLastFM();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LastFM> call, Response<LastFM> response) {
        if(response.isSuccessful()){
            LastFM topA = response.body();
            if(topA != null){
                presenter.showData(String.valueOf(topA.getTopArtists().getArtist().size()));
            }
        }
    }

    @Override
    public void onFailure(Call<LastFM> call, Throwable t) {
        presenter.showData("An error has occurred");
    }
}
