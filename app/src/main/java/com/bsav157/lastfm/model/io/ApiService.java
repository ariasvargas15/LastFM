package com.bsav157.lastfm.model.io;


import com.bsav157.lastfm.model.data.LastFM;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("2.0/?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&format=json")
    Call<LastFM> getLastFM();
}
