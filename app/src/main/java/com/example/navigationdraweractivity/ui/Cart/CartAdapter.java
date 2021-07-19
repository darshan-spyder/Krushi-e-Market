package com.example.navigationdraweractivity.ui.Cart;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdraweractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Activity context;
    ArrayList<CartModel> list;

    public CartAdapter(Activity context, ArrayList<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartModel cartModel = this.list.get(position);
        holder.txtCropCategory.setText(cartModel.getCropCategory());
        holder.txtCropName.setText(cartModel.getCropName());
        holder.txtCropQuantity.setText(cartModel.getCropQuantity());
        holder.txtCropPrice.setText(cartModel.getCropPrice());
        holder.txtCropStatus.setText(cartModel.getCropStatus());

        holder.imgCropDelete.setOnClickListener(v -> {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("CartDetail").child(cartModel.getCropKey()).removeValue();
            Intent intent = new Intent(context, CartActivity.class);
            context.startActivity(intent);
            context.finish();
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView txtCropCategory;
        TextView txtCropName;
        TextView txtCropQuantity;
        TextView txtCropPrice;
        TextView txtCropStatus;
        ImageView imgCropDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCropCategory = itemView.findViewById(R.id.txtCartCategoryName);
            txtCropName = itemView.findViewById(R.id.txtCartCropName);
            txtCropQuantity = itemView.findViewById(R.id.txtCartCropQuantity);
            txtCropPrice = itemView.findViewById(R.id.txtCartCropPrice);
            txtCropStatus = itemView.findViewById(R.id.txtCartCropStatus);

            imgCropDelete = itemView.findViewById(R.id.imgCartDelete);

        }
    }
}
