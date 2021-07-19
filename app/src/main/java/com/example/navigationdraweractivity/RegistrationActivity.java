package com.example.navigationdraweractivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private String email, fullname, contact_no, gender, password, confirmpassword;
    private EditText retemail, retfullname, retcontactno, retpassword, retconfirmpassword;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView rbsubmit = findViewById(R.id.imageView2);

        TextView tv_login1 = findViewById(R.id.pr7_login);
        tv_login1.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        rbsubmit.setOnClickListener(view -> {
            try {
                retemail = findViewById(R.id.r_et_email);
                email = retemail.getText().toString();

                retfullname = findViewById(R.id.r_et_fullname);
                fullname = retfullname.getText().toString();

                RadioGroup genderstr = findViewById(R.id.r_rg_gender);
                int select_g = genderstr.getCheckedRadioButtonId();
                RadioButton g = findViewById(select_g);
                gender = g.getText().toString();

                retcontactno = findViewById(R.id.r_et_phonenumber);
                contact_no = retcontactno.getText().toString();


                retpassword = findViewById(R.id.r_et_password);
                password = retpassword.getText().toString();

                retconfirmpassword = findViewById(R.id.r_et_confirmpassword);
                confirmpassword = retconfirmpassword.getText().toString();

                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    retemail.setError("Enter Valid Email Address.");
                    retemail.requestFocus();
                } else if (retfullname.getText().toString().isEmpty()) {
                    retfullname.setError("Enter FullName.");
                    retfullname.requestFocus();
                } else if (retcontactno.getText().toString().isEmpty()) {
                    retcontactno.setError("Enter Contact No.");
                    retcontactno.requestFocus();
                } else if (contact_no.length() != 10) {
                    retcontactno.setError("Enter Proper Contact No..");
                    retcontactno.requestFocus();
                } else if (retpassword.getText().toString().isEmpty()) {
                    retpassword.setError("Enter Password.");
                    retpassword.requestFocus();
                } else if (retpassword.length() <= 5) {
                    retpassword.setError("Password required more than 5 character.");
                    retpassword.requestFocus();
                } else if (retconfirmpassword.getText().toString().isEmpty()) {
                    retconfirmpassword.setError("Enter Confirm Password.");
                    retconfirmpassword.requestFocus();
                } else if (!password.equals(confirmpassword)) {
                    retpassword.setError("Password & Confirm password Not Matched.");
                    retpassword.requestFocus();
                } else {
                    Registration();
                }
            } catch (Exception e) {
                Toast.makeText(RegistrationActivity.this, "Something Wrong" + "\n" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Registration() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Data Uploading!!!");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            FirebaseUser user = mAuth.getCurrentUser();
            assert user != null;
            DocumentReference reference = db.collection("Registration").document(user.getUid());

            Map<String, Object> data = new HashMap<>();
            data.put("Email", email);
            data.put("Contact_no", contact_no);
            data.put("Fullname", fullname);
            data.put("Uid",user.getUid());
            data.put("Gender", gender);

            reference.set(data);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
            Map<String, Object> data1 = new HashMap<>();
            data1.put("Email", email);
            data1.put("Contact_no", contact_no);
            data1.put("Fullname", fullname);
            data1.put("Uid",user.getUid());
            ref.setValue(data1);

            Snackbar.make(findViewById(android.R.id.content), "Registration Successfully!!!", Snackbar.LENGTH_LONG).show();
            progressDialog.dismiss();


            //FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
//            SellFragment fragment = (SellFragment)fm.findFragmentById(R.id.nav_host_fragment);
//            fragment.FragmentMethod();

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.nav_host_fragment, new PriceFragment()).commit();
//            Fragment selectedFragment;
//            selectedFragment = new SellFragment();
//            RegistrationActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }).addOnFailureListener((e) -> {

            Toast.makeText(RegistrationActivity.this, "Please Registration Again" + "\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            TextView text = findViewById(R.id.r_t_registration);
            text.setText(e.getMessage());
            progressDialog.dismiss();
        });

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click Back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }


}