package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPass extends AppCompatActivity {

    private static final String TAG = "ForgotPassowrd";
    EditText userEmail;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);


        progressBar = findViewById(R.id.progressBar);
        userEmail = findViewById(R.id.user_email);
    }

    @Override
    public void onStart() {
        System.out.println("Forgot onStart");
        progressBar.setVisibility(View.GONE);
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("Forgot onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("Forgot onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("Forgot onStop");
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

    public void resetPassword(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        progressBar.setVisibility(View.VISIBLE);

        final String email = userEmail.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, "Email sent.");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPass.this);
                    builder.setMessage("We have sent an email to " + email + " for reset your password").setTitle("Email Link Sent");
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ForgotPass.this, LoginPage.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPass.this);
                    builder.setMessage(email + " not exist").setTitle("Email Error");
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressBar.setVisibility(View.GONE);
                            userEmail.setText(null);
                            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });
    }
}
