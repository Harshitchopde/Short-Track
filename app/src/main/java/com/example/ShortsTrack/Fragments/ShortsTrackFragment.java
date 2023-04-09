package com.example.ShortsTrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ShortsTrack.Adapters.ReelAdapter;
import com.example.ShortsTrack.R;

import java.util.ArrayList;


public
class ShortsTrackFragment extends Fragment {
    ArrayList<String> videoUrl;
    RecyclerView recyclerView;


    public
    ShortsTrackFragment(ArrayList<String> videoUrl) {
        // Required empty public constructor
        this.videoUrl=videoUrl;
    }
    public
    ShortsTrackFragment() {
        // Required empty public constructor
    }


    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shorts_track, container, false);

        ReelAdapter reelAdapter = new ReelAdapter(getContext(),videoUrl);
        recyclerView =view.findViewById(R.id.recycleReels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(reelAdapter);


        return view;
    }
}