package com.bsav157.lastfm.presenter;


import android.content.Context;
import android.util.Log;

import com.bsav157.lastfm.interfaces.IModel;
import com.bsav157.lastfm.interfaces.IPresenter;
import com.bsav157.lastfm.interfaces.IView;
import com.bsav157.lastfm.model.Interactor;
import com.bsav157.lastfm.model.data.Artist;
import com.bsav157.lastfm.model.data.LastFM;

import java.util.ArrayList;

public class Presenter implements IPresenter {

    private IView view;
    private IModel interactor;

    public Presenter(IView view) {
        this.view = view;
        this.interactor = new Interactor(this);
    }

    public Presenter(){
        this.interactor = new Interactor(this);
    }

    @Override
    public void makeApiQuery(String country, Context ctx) {
        if(interactor != null){
            interactor.makeApiQuery(country, ctx);
        }

    }

    @Override
    public void processData(LastFM lastFM) {
        if(lastFM != null){
            interactor.processData(lastFM);
        }
    }

    @Override
    public void showData(ArrayList<Artist> artists) {
        if(view != null){
            view.showData(artists);
        }

    }
}
