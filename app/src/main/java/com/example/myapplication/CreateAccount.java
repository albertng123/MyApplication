package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    EditText userEmail;
    EditText userPassword1;
    EditText userPassword2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userEmail = findViewById(R.id.user_email);
        userPassword1 = findViewById(R.id.user_pass);
        userPassword2 = findViewById(R.id.user_passConfirm);
        progressBar = findViewById(R.id.progressBar3);

        mAuth = FirebaseAuth.getInstance();
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

    public void signUp(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String email = userEmail.getText().toString();
        String password1 = userPassword1.getText().toString();
        String password2 = userPassword2.getText().toString();
        if(password1.equals(password2)){
            mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        user.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressBar.setVisibility(View.GONE);
                                            Log.d(TAG, "Email sent.");

                                            AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
                                            builder.setMessage("Plese verified your account via link that sent to your email").setTitle("Email Verification");
                                            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(CreateAccount.this, LoginPage.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    }
                                });
                    } else {
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(CreateAccount.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(CreateAccount.this, "Password didn't match.",Toast.LENGTH_SHORT).show();
        }
    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(CreateAccount.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}