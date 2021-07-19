package com.example.navigationdraweractivity.ui.sellhistory;

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

public class SellHistoryAdapter extends RecyclerView.Adapter<SellHistoryAdapter.ViewHolder> {

    Activity context;
    ArrayList<SellHistoryModel> list;

    public SellHistoryAdapter(Activity context, ArrayList<SellHistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    public SellHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell_history, parent, false);
        return new SellHistoryAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull SellHistoryAdapter.ViewHolder holder, int position) {

        SellHistoryModel sellHistoryModel = this.list.get(position);
        holder.txtCropCategory.setText(sellHistoryModel.getCropCategory());
        holder.txtCropName.setText(sellHistoryModel.getCropName());
        holder.txtCropQuantity.setText(sellHistoryModel.getCropQuantity());
        holder.txtCropPrice.setText(sellHistoryModel.getCropPrice());
        holder.txtCropStatus.setText(sellHistoryModel.getCropStatus());

        holder.imgSellDelete.setOnClickListener(view -> {

            FirebaseDatabase.getInstance().getReference("Users").child(sellHistoryModel.getUserUid()).child("SellHistory").child(sellHistoryModel.getSellKey()).removeValue();
            FirebaseDatabase.getInstance().getReference("SellDetails").child(sellHistoryModel.getSellKey()).removeValue();
            FirebaseDatabase.getInstance().getReference("Users").child(sellHistoryModel.getUserUid()).child("CartDetail").child(sellHistoryModel.getSellKey()).removeValue();
            //Toast.makeText(context, "Data Removed!!", Toast.LENGTH_LONG).show();
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCropCategory;
        TextView txtCropName;
        TextView txtCropQuantity;
        TextView txtCropPrice;
        TextView txtCropStatus;

        ImageView imgSellDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            txtCropCategory = itemView.findViewById(R.id.txtSellHistoryCategoryName);
            txtCropName = itemView.findViewById(R.id.txtSellHistoryCropName);
            txtCropQuantity = itemView.findViewById(R.id.txtSellHistoryCropQuantity);
            txtCropPrice = itemView.findViewById(R.id.txtSellHistoryCropPrice);
            txtCropStatus = itemView.findViewById(R.id.txtSellHistoryCropStatus);

            imgSellDelete = itemView.findViewById(R.id.imgSellDelete);

        }
    }
}
