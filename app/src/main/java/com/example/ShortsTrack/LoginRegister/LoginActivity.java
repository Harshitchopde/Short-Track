package com.example.ShortsTrack.LoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ShortsTrack.MainActivity;



import com.example.ShortsTrack.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    private TextView txtSignIn;
    private EditText use_email_enter,use_password_enter;
    private MaterialButton sign_in;
    private ProgressBar progressBar;
    private
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
ActivityLoginBinding binding;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       initvariable();
        sign_in.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            final  String User_email = use_email_enter.getText().toString().trim();
            final  String User_pswd = use_password_enter.getText().toString().trim();

            if (TextUtils.isEmpty(User_email)){
                use_email_enter.setError("Email is Require");
                return;
            }
            if(TextUtils.isEmpty(User_pswd)){
                use_password_enter.setError("Password is Require");
                return;
            }


            if (User_pswd.length()<6){
                use_password_enter.setError("Password must be greater than 6 char ");
                return;
            }
            mAuth.signInWithEmailAndPassword(User_email,User_pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public
                void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });



        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private
    void initvariable() {
        txtSignIn=binding.txtSignIn;


        use_password_enter=binding.edtSignInPassword;
        use_email_enter = binding.edtSignInEmail;
        sign_in = binding.btnSignIn;
        progressBar = binding.signInProgressBar;
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();;
    }
}