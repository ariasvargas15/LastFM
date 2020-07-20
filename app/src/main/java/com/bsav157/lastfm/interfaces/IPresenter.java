package com.bsav157.lastfm.interfaces;

import android.app.ProgressDialog;
import android.content.Context;

import com.bsav157.lastfm.model.data.Artist;
import com.bsav157.lastfm.model.data.LastFM;

import java.util.ArrayList;

public interface IPresenter {
    void makeApiQuery(String country, Context ctx, ProgressDialog pd);
    void processData(LastFM lastFM);
    void showData(ArrayList<Artist> artists);
}
