package com.bsav157.lastfm.interfaces;

import android.content.Context;

import com.bsav157.lastfm.model.data.LastFM;

public interface IModel {
    void makeApiQuery(String country, Context ctx);
    void processData(LastFM lastFM);
}
