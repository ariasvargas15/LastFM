package com.bsav157.lastfm.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.lastfm.R;
import com.bsav157.lastfm.model.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>{

    private ArrayList<Artist> artists;

    public ArtistAdapter(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false));
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);

        Picasso.get().load(artist.getImage().get(3).getText()).into(holder.image);

        holder.name.setText(artist.getName());
        holder.listeners.setText(artist.getListeners());
        holder.mbid.setText(artist.getMbid());
        holder.url.setText(artist.getUrl());

        String res = artist.getStreamable().equals("0") ? "No" : "Yes";
        holder.streamable.setText(res);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView listeners;
        TextView mbid;
        TextView url;
        TextView streamable;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.artist_image);
            name = itemView.findViewById(R.id.artist_name);
            listeners = itemView.findViewById(R.id.artist_listeners);
            mbid = itemView.findViewById(R.id.artist_mbid);
            url = itemView.findViewById(R.id.artist_url);
            streamable = itemView.findViewById(R.id.artist_streamable);
        }
    }
}
