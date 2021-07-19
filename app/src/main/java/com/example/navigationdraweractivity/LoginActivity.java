package com.example.navigationdraweractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText letemail, letpassword;
    private String email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        //firestore = FirebaseFirestore.getInstance();

        TextView txtForgot = findViewById(R.id.pro_ltvforgot);
        txtForgot.setOnClickListener(v -> ForgotPass());

        TextView tv_login = findViewById(R.id.pr7_l_b_registration);
        tv_login.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        });


        ImageView login = findViewById(R.id.pro_lblogin);
        login.setOnClickListener(v -> {

            letemail = findViewById(R.id.pro_letemail);
            email = letemail.getText().toString();
            letpassword = findViewById(R.id.pro_letpassword);
            password = letpassword.getText().toString();

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                letemail.setError("Enter Valid Email Address.");
                letemail.requestFocus();
            } else if (password.isEmpty()) {
                letpassword.setError("Enter Password");
                letpassword.requestFocus();
            } else if (email.equals("darshanadmin@gmail.com") && password.equals("darshan@admin")){

                //Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                //startActivity(intent);
                //finish();

            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(authResult -> {

                            Toast.makeText(LoginActivity.this, "Successfully Done!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }).addOnFailureListener(e ->

                        Toast.makeText(LoginActivity.this, "Authentication failed." + "n" + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void ForgotPass() {

        letemail = findViewById(R.id.pro_letemail);
        email = letemail.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            letemail.setError("Enter Valid Email Address.");
            letemail.requestFocus();
        } else {

            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Check Your Email.", Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

    }
}