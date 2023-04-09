package com.example.ShortsTrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.ShortsTrack.R;


public
class HomeFragment extends Fragment {

Toolbar toolbar;
    public
    HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        toolbar.setTitle("ShortsTrack");



        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}