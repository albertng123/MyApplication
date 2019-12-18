package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class LoginPage extends AppCompatActivity {


    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    Button signIn;
    EditText emaill;
    EditText passwordd;
    TextView createAccount,forgotPass;
    ImageView imageView;
    ProgressBar progressBar;
    TextInputLayout textInputLayoutEmail, textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.sign_in);
        emaill = findViewById(R.id.username);
        passwordd = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);
        forgotPass = findViewById(R.id.forgotPass);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar2);
        textInputLayoutEmail = findViewById(R.id.textInputLayout1);
        textInputLayoutPassword = findViewById(R.id.textInputLayout2);

        createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, CreateAccount.class);

                startActivity(intent);
            }
        });
        forgotPass = findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, ForgotPass.class);

                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            mAuth.signOut();
        }
    }

    @Override
    protected void onResume() {
        System.out.println("Login onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("Login onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("Login onStop");
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void signIn(View view) {
        final Animation shake = AnimationUtils.loadAnimation(LoginPage.this, R.anim.shake);
        String email = emaill.getText().toString();
        String password = passwordd.getText().toString();

        if (email.equals("")){
            emaill.startAnimation(shake);
            textInputLayoutEmail.startAnimation(shake);
            emaill.requestFocus();
        }
        else if (password.equals("")){
            passwordd.startAnimation(shake);
            textInputLayoutPassword.startAnimation(shake);
            passwordd.requestFocus();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            // [START sign_in_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                // Sign in success, update UI with the signed-in user's information
                                Boolean status = mAuth.getCurrentUser().isEmailVerified();
                                Boolean inNew = task.getResult().getAdditionalUserInfo().isNewUser();
                                Toast.makeText(LoginPage.this, "AAAAAAAA" + inNew.toString(), Toast.LENGTH_LONG).show();
                                Log.d(TAG, "signInWithEmail:success");
                                // Go to CurrentLocation
                                //Intent intent = new Intent(LoginPage.this, .class);
                                //startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginPage.this, "Password Wrong.", Toast.LENGTH_LONG).show();
                                passwordd.startAnimation(shake);
                                textInputLayoutPassword.startAnimation(shake);
                                passwordd.setText("");
                                passwordd.requestFocus();
                            }
                            else if (e instanceof FirebaseAuthInvalidUserException) {
                                Toast.makeText(LoginPage.this, "Email Wrong.", Toast.LENGTH_LONG).show();
                                emaill.startAnimation(shake);
                                textInputLayoutEmail.startAnimation(shake);
                                emaill.setText("");
                                emaill.requestFocus();
                            }
                        }
                    });
            // [END sign_in_with_email]
        }
    }
}
