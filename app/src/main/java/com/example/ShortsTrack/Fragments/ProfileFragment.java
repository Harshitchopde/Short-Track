package com.example.ShortsTrack.Fragments;

import static android.app.Activity.RESULT_OK;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ShortsTrack.LoginRegister.LoginActivity;
import com.example.ShortsTrack.Module.Users;
import com.example.ShortsTrack.ProfileUpdate;
import com.example.ShortsTrack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public
class ProfileFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_VIDEO = 1;
    private static final int REQUEST_CODE_PICK_PHOTO = 2;
    FirebaseAuth mauth;
    FirebaseDatabase database;
    private RelativeLayout mProfileSection;
    private ImageView mProfilePicture, Uploads;
    private TextView mProfileName, mPosts;
    private Uri ReelPath;
    private Button LogOut;

    public
    ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfileSection = view.findViewById(R.id.profile_section);
        mProfilePicture = view.findViewById(R.id.profile_picture);
        mProfileName = view.findViewById(R.id.profile_name);
        Uploads = view.findViewById(R.id.Upload);
        mPosts = view.findViewById(R.id.posts);
        LogOut = view.findViewById(R.id.logout);
        database = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();

        Uploads.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            intent.setType("video/*");
            startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);
        });
        LogOut.setOnClickListener(view1 -> {
            mauth.signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        });
        mProfilePicture.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(),ProfileUpdate.class));
        });

        return view;
    }


    @Override
    public
    void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            ReelPath = data.getData();
            // Do something with the videoUri, e.g. load it into a video player or upload it to a server
            UploadingVideo(ReelPath);

        } else if (requestCode == REQUEST_CODE_PICK_PHOTO && resultCode == RESULT_OK && data != null) {

        }
    }

    private
    void UploadingVideo(Uri reelPath) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("Reels/" + UUID.randomUUID().toString())
                .putFile(reelPath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public
                    void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
//                            Log.d("UPload", "onComplete: uploaded");
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public
                                void onComplete(@NonNull Task<Uri> task) {
//                                    Users users = new Users(User_name,User_email,task.getResult().getUser().getUid(),"",0);
                                    String id = mauth.getCurrentUser().getUid();
                                    database.getReference().child("Myreels/" + id).push().child(task.getResult().toString()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public
                                        void onDataChange(@NonNull DataSnapshot snapshot) {
//                                           Reference().child("user/"+id+"/posts").setValue(posts);
                                            Log.e(TAG, "onDataChange: " + snapshot.getChildren().toString());
                                        }

                                        @Override
                                        public
                                        void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            });
                        } else {

                        }
                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public
                    void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = 100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });
    }

}

