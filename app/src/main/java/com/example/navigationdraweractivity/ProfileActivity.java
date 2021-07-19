package com.example.navigationdraweractivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    EditText edtProfileName;
    EditText edtProfileContact;
    EditText edtProfileEmail;
    EditText edtProfileGender;
    ImageView imgProfile;
    ImageView imgEditProfileImage;

    String Email;

    Button btnUpdate;

    private Uri filePath;
    private Uri downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = findViewById(R.id.imgProfile);
        imgEditProfileImage = findViewById(R.id.imgEditProfileImage);
        imgProfile.setOnClickListener(v -> SelectImage());

        edtProfileName = findViewById(R.id.edtProfileFullName);
        edtProfileContact = findViewById(R.id.edtProfileContact);
        edtProfileEmail = findViewById(R.id.edtProfileEmail);
        edtProfileGender = findViewById(R.id.edtProfileGender);

        FetchProfileDetail();

        btnUpdate = findViewById(R.id.btnProfileUpdate);
        btnUpdate.setOnClickListener(v -> UpdateProfile());

    }

    private void UpdateProfile() {

        String UpdateFullName = edtProfileName.getText().toString();
        String UpdateContact = edtProfileContact.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
//        DocumentReference ref = FirebaseFirestore.getInstance().collection("Registration").document(user.getUid());
//        final Map<String, Object> data = new HashMap<>();
//        data.put("Fullname", UpdateFullName);
//        data.put("Contact_no", UpdateContact);
//        ref.update(data);
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
//        reference.updateChildren(data);

        //Image Upload data//
        ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setTitle("Uploading Image....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        if (filePath != null) {

            StorageReference sRef = FirebaseStorage.getInstance().getReference("Users").child(Email + "Profile" + "." + getFileExtension(filePath));

            //data.put("mimagename", UpdateFullName + "Profile");
            sRef.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (true) {
                            if (urlTask.isSuccessful()) break;
                        }
                        downloadUrl = urlTask.getResult();

                        final Map<String, Object> data2 = new HashMap<>();
                        data2.put("ProfileUrl", downloadUrl.toString());
                        data2.put("Fullname", UpdateFullName);
                        data2.put("Contact_no", UpdateContact);

                        assert user != null;
                        FirebaseFirestore.getInstance().collection("Registration").document(user.getUid()).update(data2);

                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                        Map<String, Object> data11 = new HashMap<>();
                        data11.put("Fullname", UpdateFullName);
                        data11.put("Contact_no", UpdateContact);
                        data11.put("ProfileUrl", downloadUrl.toString());
                        reference1.updateChildren(data11);

                        Snackbar.make(findViewById(android.R.id.content), "Data & Image Updated.", Snackbar.LENGTH_LONG).show();

                    })
                    .addOnFailureListener(exception -> {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "Data & Image not Updated.", Toast.LENGTH_LONG).show();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progressPrecent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Progress: " + (float) progressPrecent + "%");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setCancelable(false);
                    });
        } else {

            Toast.makeText(this, "Image not Selected", Toast.LENGTH_LONG).show();

        }
    }


    private void FetchProfileDetail() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        DocumentReference reference = FirebaseFirestore.getInstance().collection("Registration").document(user.getUid());
        reference.addSnapshotListener((value, error) -> {
            String Name = Objects.requireNonNull(value).getString("Fullname");
            String Contact = Objects.requireNonNull(value).getString("Contact_no");
            Email = Objects.requireNonNull(value).getString("Email");
            String Gender = Objects.requireNonNull(value).getString("Gender");
            String Profile = value.getString("ProfileUrl");

            edtProfileName.setText(Name);
            edtProfileContact.setText(Contact);
            edtProfileEmail.setText(Email);
            edtProfileGender.setText(Gender);
            Glide.with(getApplicationContext()).load(Profile).diskCacheStrategy(DiskCacheStrategy.NONE).into(imgProfile);

        });
    }

    private void SelectImage() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        if (resultCode == RESULT_OK) {
//
//            filePath = imageReturnedIntent.getData();
//            Uri selectedImage = imageReturnedIntent.getData();
//
//            imgCustomerProfileImage.setImageURI(selectedImage);
//
//        }

        if (resultCode == RESULT_OK && imageReturnedIntent != null) {
            filePath = imageReturnedIntent.getData();
            Toast.makeText(ProfileActivity.this, "path", Toast.LENGTH_LONG).show();

            try {
                Toast.makeText(ProfileActivity.this, "in if", Toast.LENGTH_LONG).show();

                Toast.makeText(ProfileActivity.this, filePath.toString(), Toast.LENGTH_LONG).show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ProfileActivity.this.getContentResolver(), filePath);
                imgProfile.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ProfileActivity.this, "PHOTO NOT INSERT", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

}