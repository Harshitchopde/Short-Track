package com.example.ShortsTrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ShortsTrack.R;


public
class ShortsTrackFragment extends Fragment {


    public
    ShortsTrackFragment() {
        // Required empty public constructor
    }


    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shorts_track, container, false);
    }
}