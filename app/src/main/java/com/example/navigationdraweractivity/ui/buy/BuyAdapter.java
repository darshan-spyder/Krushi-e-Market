package com.example.navigationdraweractivity.ui.buy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navigationdraweractivity.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class BuyAdapter extends  FirebaseRecyclerAdapter<BuyModel, BuyAdapter.ViewHolder>{

    public BuyAdapter(@NonNull FirebaseRecyclerOptions<BuyModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final BuyModel model) {

        holder.txtCropCategory.setText(model.getCrop_category());
        holder.txtCropName.setText(model.getCrop_name());
        holder.txtCropQuantity.setText(model.getCrop_quantity());
        holder.txtCropPrice.setText(model.getCrop_price());
        holder.txtCropDistrict.setText(model.getDistrict());

        if(model.getCrop_name().equals("Millet")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.millet).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Rice")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.rice).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Corn")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.corn).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Wheat")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.wheat).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Sorghum")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.sorghum).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Pigeon pea")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.pigeon_pea).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Chickpeas(Brown)")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.chickpeas_brown).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Cowpea")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.cowpea).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Chickpeas(White)")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.chickpeas_white).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Red lentils")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.red_lentils).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Pea")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.pea).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Mung beans")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.mung_bean).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Moth beans")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.moth_beans).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Kidney beans")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.kidney_bean).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Sesame")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.sesame).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Urad dal")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.urad_daal).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Field beans")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.field_beans).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Bay leaf")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.bay_leaf).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Cardamom")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.cardamom).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Cinnamon")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.cinamon).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Cumin")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.cumin).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Star anise")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.star_anise).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Peanut")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.peunut).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Sugarcane")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.sugarcane).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Cotton")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.cotton).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Tea")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.tea).into(holder.imgCropProfile);
        }
        if(model.getCrop_name().equals("Coffee")) {
            Glide.with(holder.imgCropProfile.getContext()).load(R.drawable.coffee).into(holder.imgCropProfile);
        }


        holder.cardView.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), BuyFullDetailActivity.class);
            intent.putExtra("CropCategory", model.getCrop_category());
            intent.putExtra("CropName", model.getCrop_name());
            intent.putExtra("CropQuantity", model.getCrop_quantity());
            intent.putExtra("CropPrice", model.getCrop_price());
            intent.putExtra("District", model.getDistrict());
            intent.putExtra("Uid", model.getUid());

            intent.putExtra("Taluka", model.getTaluka());
            intent.putExtra("Village", model.getVillage());
            intent.putExtra("FarmAddress", model.getCrop_farmaddress());
            intent.putExtra("OtherDetail", model.getOther_details());
            intent.putExtra("CropKey",model.getKey());

//            dbr1.get().addOnSuccessListener(documentSnapshot -> {
//                intent.putExtra("CropKey",documentSnapshot.getKey());
//            });


            DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Users").child(model.getUid());
            dbr.get().addOnSuccessListener(documentSnapshot -> {

                intent.putExtra("Email", Objects.requireNonNull(documentSnapshot.child("Email").getValue()).toString());
                intent.putExtra("FullName", Objects.requireNonNull(documentSnapshot.child("Fullname").getValue()).toString());
                intent.putExtra("ContactNo", Objects.requireNonNull(documentSnapshot.child("Contact_no").getValue()).toString());

//                String Email = documentSnapshot.child("Email").getValue().toString();
//                String FullName = documentSnapshot.child("Fullname").getValue().toString();
//                String Contact = documentSnapshot.child("Contact_no").getValue().toString();

//                 = documentSnapshot.getString("Email");
                view.getContext().startActivity(intent);

//                Fragment selectedFragment;
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                selectedFragment = new BuyFullDetail(Email,FullName,Contact,model.getCrop_category(), model.getCrop_name(), model.getCrop_quantity(), model.getCrop_price(), model.getDistrict(), model.getTaluka(), model.getVillage(), model.getCrop_farmaddress(), model.getOther_details(), model.getUid());
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment,null).addToBackStack(null).commit();
//
//                 AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.buyfragment, new BuyFullDetail(Email,FullName,Contact,model.getCrop_category(), model.getCrop_name(), model.getCrop_quantity(), model.getCrop_price(), model.getDistrict(), model.getTaluka(), model.getVillage(), model.getFarmerAddress(), model.getOtherDetail(), model.getUid())).addToBackStack(null).commit();

            });

        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCropProfile;
        TextView txtCropCategory;
        TextView txtCropName;
        TextView txtCropQuantity;
        TextView txtCropPrice;
        TextView txtCropDistrict;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCropProfile = itemView.findViewById(R.id.imgCropProfile);
            txtCropCategory = itemView.findViewById(R.id.txtCropCategory);
            txtCropName = itemView.findViewById(R.id.txtCropName);
            txtCropQuantity = itemView.findViewById(R.id.txtCropQuantity);
            txtCropPrice = itemView.findViewById(R.id.txtCropPrice);
            txtCropDistrict = itemView.findViewById(R.id.txtCropDistrict);

            cardView = itemView.findViewById(R.id.cardBuyList);
        }


    }


}