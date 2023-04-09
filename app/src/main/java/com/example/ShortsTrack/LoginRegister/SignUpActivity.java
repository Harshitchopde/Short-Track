package com.example.ShortsTrack.LoginRegister;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShortsTrack.MainActivity;

import com.example.ShortsTrack.Module.Users;
import com.example.ShortsTrack.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

   private TextView txtSignUp;
   private EditText use_name,use_email,use_password1,use_cmpassword;
   private MaterialButton sign_up;
   private ProgressBar progressBar;
    private
    ActivitySignUpBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initVariable();

        if (firebaseUser!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        sign_up.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            final  String User_name = use_name.getText().toString().trim();
            final  String User_email = use_email.getText().toString().trim();
            final  String User_cmpswd = use_cmpassword.getText().toString().trim();
            final  String User_pswd = use_password1.getText().toString().trim();
            if (TextUtils.isEmpty(User_name)){
                use_name.setError("Name is Require");
                return;
            }
            if (TextUtils.isEmpty(User_email)){
                use_email.setError("Email is Require");
                return;
            }
            if(TextUtils.isEmpty(User_pswd)){
                use_password1.setError("Password is Require");
                return;
            }
            if(TextUtils.isEmpty(User_cmpswd)){
                use_cmpassword.setError("Password is Require");
                return;
            }
            if (!(User_pswd.equals(User_cmpswd))){
                use_cmpassword.setError("not match with above");
                use_cmpassword.setText("");
                return;
            }
            if (User_pswd.length()<6){
                use_password1.setError("Password must be greater than 6 char ");
                return;
            }
            mAuth.createUserWithEmailAndPassword(User_email,User_cmpswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public
                void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "User Register Successfull", Toast.LENGTH_SHORT).show();
                        Users users = new Users(User_name,User_email,task.getResult().getUser().getUid(),"",0);
                        String id = mAuth.getCurrentUser().getUid();
                        database.getReference().child("user/"+id).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public
                            void onSuccess(Void unused) {
                                Toast.makeText(SignUpActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                                database.getReference("Myreels/"+id).push().setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public
                                    void onSuccess(Void unused) {
                                        Toast.makeText(SignUpActivity.this, "online storage created", Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "onSuccess: "+"online storage created" );
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public
                            void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, "Fail due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onFailure: 0 "+e.getMessage() );
                            }
                        });
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });



        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private
    void initVariable() {
        txtSignUp = binding.txtSignUp;
        use_name=binding.edtSignUpFullName;
        use_password1=binding.edtSignUpPassword;
        use_cmpassword = binding.edtSignUpConfirmPassword;
        use_email = binding.edtSignUpEmail;
        sign_up = binding.btnSignUp;
        progressBar = binding.signUpProgressBar;
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();;
        database = FirebaseDatabase.getInstance();
    }
}