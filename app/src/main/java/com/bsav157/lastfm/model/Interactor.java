package com.bsav157.lastfm.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    private String country;
    private ProgressDialog progressDialog;

    public Interactor(IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeApiQuery(String country, Context ctx, ProgressDialog pd) {
        myCtx = ctx;
        this.country = country;
        progressDialog = pd;
        Call<LastFM> call = ApiAdapter.getApiService().getLastFM(METHOD, country, KEY, FORMAT);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LastFM> call, Response<LastFM> response) {
        if (response.isSuccessful()) {
            boolean flag = false;
            Intent in = new Intent(myCtx, ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("country", country);
            try {
                LastFM lastFM = response.body();
                if (lastFM == null || lastFM.getTopArtists().getArtist().isEmpty()){
                    flag = true;
                } else{
                    bundle.putSerializable("LastFM", lastFM);
                }
            } catch (Exception e) {
                flag = true;
            } finally {
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(flag){
                    bundle.putBoolean("message", true);
                }
                in.putExtras(bundle);
                myCtx.startActivity(in);
                progressDialog.dismiss();
            }

        }
    }

    @Override
    public void onFailure(Call<LastFM> call, Throwable t) {
        showMessage();
    }

    @Override
    public void processData(LastFM lastFM) {
        if (lastFM != null && presenter != null) {
            presenter.showData((ArrayList<Artist>) lastFM.getTopArtists().getArtist());
        }

    }

    private void showMessage() {
        if (presenter != null) {
            presenter.showMessage("THERE IS NO DATA FOR THIS COUNTRY, TRY WITH ANOTHER");
        }
    }
}
