package com.example.navigationdraweractivity.ui.buyhistory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdraweractivity.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BuyHistoryAdapter extends RecyclerView.Adapter<BuyHistoryAdapter.ViewHolder> {

    Activity context;
    ArrayList<BuyHistoryModel> list;

    public BuyHistoryAdapter(Activity context, ArrayList<BuyHistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    public BuyHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyHistoryAdapter.ViewHolder holder, int position) {
        BuyHistoryModel buyHistoryModel = this.list.get(position);
        holder.txtCropCategory.setText(buyHistoryModel.getCropCategory());
        holder.txtCropName.setText(buyHistoryModel.getCropName());
        holder.txtCropQuantity.setText(buyHistoryModel.getCropQuantity());
        holder.txtCropPrice.setText(buyHistoryModel.getCropPrice());

        holder.imageView.setOnClickListener(view -> {

            FirebaseDatabase.getInstance().getReference("Users").child(buyHistoryModel.getUserUid()).child("BuyHistory").child(buyHistoryModel.getBuyKey()).removeValue();

            //Toast.makeText(context, "Data Removed!!", Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtCropCategory;
        TextView txtCropName;
        TextView txtCropQuantity;
        TextView txtCropPrice;
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            txtCropCategory = itemView.findViewById(R.id.txtBuyHistoryCategoryName);
            txtCropName = itemView.findViewById(R.id.txtBuyHistoryCropName);
            txtCropQuantity = itemView.findViewById(R.id.txtBuyHistoryCropQuantity);
            txtCropPrice = itemView.findViewById(R.id.txtBuyHistoryCropPrice);
            imageView = itemView.findViewById(R.id.imgBuyDelete);
        }
    }
}
