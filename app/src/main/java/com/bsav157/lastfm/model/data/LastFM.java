package com.bsav157.lastfm.model.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastFM {

    @SerializedName("topartists")
    @Expose
    private TopArtists topArtists;

    public TopArtists getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

}