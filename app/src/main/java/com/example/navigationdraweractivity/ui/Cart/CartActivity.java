package com.example.navigationdraweractivity.ui.Cart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdraweractivity.LoginActivity;
import com.example.navigationdraweractivity.MainActivity;
import com.example.navigationdraweractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String CropStatus;
    String CropKey;
    String FarmAddress;
    String OtherDetail;
    String District;
    String Taluka;
    String Village;

    ArrayList<CartModel> cartModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        FetchCartCropDetail();

        Button btnOrder = findViewById(R.id.btnBuyProduct);
        btnOrder.setOnClickListener(v -> BuyHistory());

    }

    private void FetchCartCropDetail() {

        cartModelArrayList.clear();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);

            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("You are Not Login.");
            alertDialog.setMessage("Please Login.");
            alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> {

                Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            });
            alertDialog.show();

        } else {
            assert user != null;
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("CartDetail");
            reference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    cartModelArrayList.clear();
                    int Count = 0;
                    int Price = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        CropCategory = Objects.requireNonNull(dataSnapshot.child("Crop_category").getValue()).toString();
                        CropName = Objects.requireNonNull(dataSnapshot.child("Crop_name").getValue()).toString();
                        CropQuantity = Objects.requireNonNull(dataSnapshot.child("Crop_quantity").getValue()).toString();
                        CropPrice = Objects.requireNonNull(dataSnapshot.child("Crop_price").getValue()).toString();
                        CropStatus = Objects.requireNonNull(dataSnapshot.child("Status").getValue()).toString();

                        FarmAddress = Objects.requireNonNull(dataSnapshot.child("Crop_farmaddress").getValue()).toString();
                        OtherDetail = Objects.requireNonNull(dataSnapshot.child("Other_details").getValue()).toString();
                        District = Objects.requireNonNull(dataSnapshot.child("District").getValue()).toString();
                        Taluka = Objects.requireNonNull(dataSnapshot.child("Taluka").getValue()).toString();
                        Village = Objects.requireNonNull(dataSnapshot.child("Village").getValue()).toString();

                        CropKey = dataSnapshot.getKey();
                        cartModelArrayList.add(new CartModel(CropCategory, CropName, CropQuantity, CropPrice, CropStatus, CropKey));

                        Count++;
                        Price = Price + Integer.parseInt(CropPrice);
                        TextView txt = findViewById(R.id.txtCartCropCount);
                        txt.setText(String.valueOf(Count));
                        TextView txt1 = findViewById(R.id.txtCartTotal);
                        txt1.setText(Price + " " + "\u20B9");
                    }

                    ShowCartDetail();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(CartActivity.this, "Error!!!" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void ShowCartDetail() {

        RecyclerView recyclerView = findViewById(R.id.rvCart);
        CartAdapter adapter = new CartAdapter(CartActivity.this, cartModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));

    }

    private void BuyHistory() {
        ProgressDialog progressDialog = new ProgressDialog(CartActivity.this);
        progressDialog.setTitle("Data Uploading!!!");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("BuyHistory").child(CropKey);

        Map<String, Object> data = new HashMap<>();
        data.put("Crop_category", CropCategory);
        data.put("Crop_farmaddress", FarmAddress);
        data.put("Crop_name", CropName);
        data.put("Crop_price", CropPrice);
        data.put("Crop_quantity", CropQuantity);
        data.put("Other_details", OtherDetail);
        data.put("District", District);
        data.put("Taluka", Taluka);
        data.put("Village", Village);
        data.put("Uid", user.getUid());
        data.put("Status", "Buy");
        reference.setValue(data);

        FirebaseDatabase.getInstance().getReference().child("SellDetails").child(CropKey).child("Status").setValue("Buy");

        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("CartDetail").removeValue();

        progressDialog.dismiss();

        Toast.makeText(CartActivity.this, "Crop Buy!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}