package com.bsav157.lastfm.model.io;


import com.bsav157.lastfm.model.data.LastFM;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("2.0/")
    Call<LastFM> getLastFM(
            @Query("method") String method,
            @Query("country") String country,
            @Query("api_key") String key,
            @Query("format") String format);
}
