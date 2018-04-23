package com.example.shivamvk.moviesimdb;

import android.content.Intent;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupWithEmailActivity extends AppCompatActivity {

    private Button btSignupEmail;
    private EditText etSignupEmail;
    private EditText etSignupPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_with_email);

        btSignupEmail = findViewById(R.id.bt_signup_email);
        etSignupEmail = findViewById(R.id.et_signup_email);
        etSignupPassword = findViewById(R.id.et_signup_password);

        firebaseAuth = FirebaseAuth.getInstance();

        btSignupEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyInputs();
            }
        });
    }

    private void verifyInputs(){
        String stEmail = etSignupEmail.getText().toString();
        String stPassword = etSignupPassword.getText().toString();

        if (stEmail.isEmpty()){
            etSignupEmail.setError("Email is required");
            etSignupEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(stEmail).matches()){
            etSignupEmail.setError("Enter a valid email address");
            etSignupEmail.requestFocus();
            return;
        }

        if (stPassword.isEmpty()){
            etSignupPassword.setError("Password is required");
            etSignupPassword.requestFocus();
            return;
        }

        if (stPassword.length() < 6){
            etSignupPassword.setError("Minimum 6 characters required");
            etSignupPassword.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(stEmail, stPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupWithEmailActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                            updateUI(firebaseAuth.getCurrentUser());
                        }
                        else{
                            Toast.makeText(SignupWithEmailActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser){
        finish();
        Intent intent = new Intent(SignupWithEmailActivity.this, HomeActivity.class);
        intent.putExtra("FirebaseUser", firebaseUser);
        startActivity(intent);
    }
}
