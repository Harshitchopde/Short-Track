package com.example.ShortsTrack;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ShortsTrack.Fragments.HomeFragment;
import com.example.ShortsTrack.Fragments.LiveFragment;
import com.example.ShortsTrack.Fragments.ProfileFragment;
import com.example.ShortsTrack.Fragments.SavedFragment;
import com.example.ShortsTrack.Fragments.ShortsTrackFragment;
import com.example.ShortsTrack.Module.VideoUrl;
import com.example.ShortsTrack.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public
class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BottomNavigationView btmNavView;
FirebaseAuth mauth;
    private boolean isResumed = false;

    FirebaseDatabase database;
ArrayList<String> videoUrl;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mauth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();
        videoUrl = new ArrayList<>();
        database.getReference("Reels").addValueEventListener(new ValueEventListener() {
            @Override
            public
            void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e(TAG, "onDataChange: "+snapshot.getChildren().toString() );
                for (DataSnapshot snapshot1:snapshot.getChildren() ){
                    String url = snapshot1.getValue(String.class);
                    videoUrl.add(url);
                    Log.e(TAG, "onDataChange: "+url );

                }
            }

            @Override
            public
            void onCancelled(@NonNull DatabaseError error) {

            }
        });





        if (!checkPermission()) {
            requestPermissions();
            return;

        }

        loadFrag(new HomeFragment(),1);
        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public
            boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (R.id.navigation_home==id){
                 loadFrag(new HomeFragment(),0);

                }else if (R.id.navigation_saved==id){
                    loadFrag(new SavedFragment(),0);
                }
                else if (R.id.navigation_shotstrack==id){
                    loadFrag(new ShortsTrackFragment(videoUrl),0);

                }
                else if (R.id.navigation_live==id){
                    loadFrag(new LiveFragment(),0);

                }
                else
                {
                    loadFrag(new ProfileFragment(),0);
                }

                return true;

            }
        });

    }

    private
    void loadFrag(Fragment fragment, int flag) {
//         data passing in fragment
        Bundle bundle = new Bundle();
        bundle.putString("name","Harshit");
        bundle.putInt("roll",25);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag==1){
        ft.add(R.id.frame_laout,fragment);

        }
        else {
        ft.replace(R.id.frame_laout,fragment);
        ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public
    void onBackPressed() {

        super.onBackPressed();
    }



    private
    void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "READ PERMISSION IS REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT);
        }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},123);

        }
    }


    boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
//  btmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//@Override
//public
//            boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        }
//        });
//
//        }
//public  void loadFrag(Fragment fragment,int flag){
//
//        }