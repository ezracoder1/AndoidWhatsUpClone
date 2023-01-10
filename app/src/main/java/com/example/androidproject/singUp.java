package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class singUp extends AppCompatActivity {
    ActivitySingUpBinding binding;


    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(singUp.this);
        progressDialog.setTitle("creating Account");
        progressDialog.setMessage("creating account ");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.txtusername.getText().toString().isEmpty() && !binding.txtEmail.getText().toString().isEmpty() && !binding.txtusername.getText().toString().isEmpty()) {
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(binding.txtEmail.getText().toString(), binding.txtpassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        users use=new users(binding.txtusername.getText().toString(),binding.txtEmail.getText().toString(),binding.txtpassword.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        database.getReference().child("users").child(id).setValue(use);
                                        Toast.makeText(singUp.this, "SingUp successfuly", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(singUp.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(singUp.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.txtAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(singUp.this,signin.class);
                startActivity(intent);
            }
        });

    }
}