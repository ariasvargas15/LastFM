package com.bsav157.lastfm.interfaces;

import android.app.ProgressDialog;
import android.content.Context;

import com.bsav157.lastfm.model.data.LastFM;

public interface IModel {
    void makeApiQuery(String country, Context ctx, ProgressDialog pd);
    void processData(LastFM lastFM);
}
