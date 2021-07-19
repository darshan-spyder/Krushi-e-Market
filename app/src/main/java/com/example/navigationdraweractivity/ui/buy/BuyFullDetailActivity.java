package com.example.navigationdraweractivity.ui.buy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.navigationdraweractivity.MainActivity;
import com.example.navigationdraweractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BuyFullDetailActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtEmail;
    TextView txtContact;
    TextView txtCropCategory;
    TextView txtCropName;
    TextView txtCropQuantity;
    TextView txtCropPrice;
    TextView txtDistrict;
    TextView txtTaluka;
    TextView txtVillage;
    TextView txtFarmAddress;
    TextView txtOtherDetail;
    ImageView imgProfile;

    String Name;
    String Email;
    String Contact;
    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String District;
    String Taluka;
    String Village;
    String FarmAddress;
    String OtherDetail;

    String CropKey;
    Button btnBuyCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_full_detail);

        imgProfile = findViewById(R.id.imgBuyProfile);
        txtName = findViewById(R.id.txtSellName);
        txtEmail = findViewById(R.id.txtSellEmail);
        txtContact = findViewById(R.id.txtSellContact);
        txtCropCategory = findViewById(R.id.txtBuyCropCategory);
        txtCropPrice = findViewById(R.id.txtBuyCropPrice);
        txtCropQuantity = findViewById(R.id.txtBuyCropQuantity);
        txtCropName = findViewById(R.id.txtBuyCropName);
        txtDistrict = findViewById(R.id.txtSellDistrict);
        txtTaluka = findViewById(R.id.txtSellTaluko);
        txtVillage = findViewById(R.id.txtSellVillage);
        txtFarmAddress = findViewById(R.id.txtBuyFarmerAddress);
        txtOtherDetail = findViewById(R.id.txtBuyOtherDetails);

        Name = getIntent().getExtras().getString("FullName");
        txtName.setText(Name);
        Email = getIntent().getExtras().getString("Email");
        txtEmail.setText(Email);
        Contact = getIntent().getExtras().getString("ContactNo");
        txtContact.setText(Contact);
        CropCategory = getIntent().getExtras().getString("CropCategory");
        txtCropCategory.setText(CropCategory);
        CropPrice = getIntent().getExtras().getString("CropPrice");
        txtCropPrice.setText(CropPrice);
        CropName = getIntent().getExtras().getString("CropName");
        txtCropName.setText(CropName);
        CropQuantity = getIntent().getExtras().getString("CropQuantity");
        txtCropQuantity.setText(CropQuantity);
        District = getIntent().getExtras().getString("District");
        txtDistrict.setText(District);
        Taluka = getIntent().getExtras().getString("Taluka");
        txtTaluka.setText(Taluka);
        Village = getIntent().getExtras().getString("Village");
        txtVillage.setText(Village);
        FarmAddress = getIntent().getExtras().getString("FarmAddress");
        txtFarmAddress.setText(FarmAddress);
        OtherDetail = getIntent().getExtras().getString("OtherDetail");
        txtOtherDetail.setText(OtherDetail);
        CropKey = getIntent().getExtras().getString("CropKey");

        if(CropName.equals("Millet")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.millet).into(imgProfile);
        }
        if(CropName.equals("Rice")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.rice).into(imgProfile);
        }
        if(CropName.equals("Corn")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.corn).into(imgProfile);
        }
        if(CropName.equals("Wheat")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.wheat).into(imgProfile);
        }
        if(CropName.equals("Sorghum")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.sorghum).into(imgProfile);
        }
        if(CropName.equals("Pigeon pea")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.pigeon_pea).into(imgProfile);
        }
        if(CropName.equals("Chickpeas(Brown)")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.chickpeas_brown).into(imgProfile);
        }
        if(CropName.equals("Cowpea")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.cowpea).into(imgProfile);
        }
        if(CropName.equals("Chickpeas(White)")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.chickpeas_white).into(imgProfile);
        }
        if(CropName.equals("Red lentils")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.red_lentils).into(imgProfile);
        }
        if(CropName.equals("Pea")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.pea).into(imgProfile);
        }
        if(CropName.equals("Mung beans")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.mung_bean).into(imgProfile);
        }
        if(CropName.equals("Moth beans")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.moth_beans).into(imgProfile);
        }
        if(CropName.equals("Kidney beans")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.kidney_bean).into(imgProfile);
        }
        if(CropName.equals("Sesame")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.sesame).into(imgProfile);
        }
        if(CropName.equals("Urad dal")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.urad_daal).into(imgProfile);
        }
        if(CropName.equals("Field beans")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.field_beans).into(imgProfile);
        }
        if(CropName.equals("Bay leaf")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.bay_leaf).into(imgProfile);
        }
        if(CropName.equals("Cardamom")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.cardamom).into(imgProfile);
        }
        if(CropName.equals("Cinnamon")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.cinamon).into(imgProfile);
        }
        if(CropName.equals("Cumin")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.cumin).into(imgProfile);
        }
        if(CropName.equals("Star anise")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.star_anise).into(imgProfile);
        }
        if(CropName.equals("Peanut")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.peunut).into(imgProfile);
        }
        if(CropName.equals("Sugarcane")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.sugarcane).into(imgProfile);
        }
        if(CropName.equals("Cotton")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.cotton).into(imgProfile);
        }
        if(CropName.equals("Tea")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.tea).into(imgProfile);
        }
        if(CropName.equals("Coffee")) {
            Glide.with(imgProfile.getContext()).load(R.drawable.coffee).into(imgProfile);
        }

        btnBuyCrop = findViewById(R.id.btnCartCrop);
        btnBuyCrop.setOnClickListener(v -> CartCrop());

    }

    private void CartCrop() {

        ProgressDialog progressDialog = new ProgressDialog(BuyFullDetailActivity.this);
        progressDialog.setTitle("Data Uploading!!!");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("CartDetail").child(CropKey);

        Map<String, Object> data = new HashMap<>();
        data.put("Crop_category", CropCategory);
        data.put("Crop_farmaddress", FarmAddress);
        data.put("Crop_name", CropName);
        data.put("Crop_price", CropPrice);
        data.put("Crop_quantity",CropQuantity );
        data.put("Other_details", OtherDetail);
        data.put("District", District);
        data.put("Taluka", Taluka);
        data.put("Village", Village);
        data.put("Uid", user.getUid());
        data.put("Status", "Pending");

        reference.setValue(data);

        progressDialog.dismiss();

        Toast.makeText(BuyFullDetailActivity.this, "Add to Cart!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(BuyFullDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}