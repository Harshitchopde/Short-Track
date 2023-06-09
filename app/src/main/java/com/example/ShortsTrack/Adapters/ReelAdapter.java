package com.example.ShortsTrack.Adapters;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ShortsTrack.Module.Video;
import com.example.ShortsTrack.R;

import java.util.ArrayList;

public
class ReelAdapter extends RecyclerView.Adapter<ReelAdapter.ViewHolder> {

Context context;
ArrayList<String> videoArrayList;

    public
    ReelAdapter(Context context, ArrayList<String> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public
    ReelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shortstrack,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull ReelAdapter.ViewHolder holder, int position) {
        holder.videoView.setVideoPath(String.valueOf(videoArrayList.get(position)));
        holder.videoView.start();
    }

    @Override
    public
    int getItemCount() {
        return videoArrayList.size();
    }

    public
    class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.reelsVideo);
        }
    }
}
