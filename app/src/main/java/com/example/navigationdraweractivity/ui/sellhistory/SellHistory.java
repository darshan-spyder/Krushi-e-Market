package com.example.navigationdraweractivity.ui.sellhistory;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationdraweractivity.LoginActivity;
import com.example.navigationdraweractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class SellHistory extends Fragment {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String SellKey;
    String CropStatus;


    ArrayList<SellHistoryModel> sellHistoryModelArrayList = new ArrayList<>();
    FirebaseDatabase dbr = FirebaseDatabase.getInstance();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sell_history, container, false);

        FetchSellHistory(root);

        return root;
    }

    private void FetchSellHistory(View root) {

        FirebaseUser UsersUid = mAuth.getCurrentUser();

        if (UsersUid == null) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("You are Not Login.");
            alertDialog.setMessage("Please Login.");
            alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> {

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            });
            alertDialog.show();

        } else {
            assert UsersUid != null;

            Query reference = dbr.getReference("SellDetails").orderByChild("Uid").equalTo(UsersUid.getUid());
            //DatabaseReference reference = dbr.getReference("Users").child(UsersUid.getUid()).child("BuyHistory");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    sellHistoryModelArrayList.clear();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {

                        CropCategory = Objects.requireNonNull(childSnapshot.child("Crop_category").getValue()).toString();
                        CropName = Objects.requireNonNull(childSnapshot.child("Crop_name").getValue()).toString();
                        CropQuantity = Objects.requireNonNull(childSnapshot.child("Crop_quantity").getValue()).toString();
                        CropPrice = Objects.requireNonNull(childSnapshot.child("Crop_price").getValue()).toString();
                        CropStatus = Objects.requireNonNull(childSnapshot.child("Status").getValue()).toString();

                        SellKey = childSnapshot.getKey();

                        sellHistoryModelArrayList.add(new SellHistoryModel(CropCategory, CropName, CropQuantity, CropPrice, CropStatus, UsersUid.getUid(), SellKey));
                    }
                    showBuyHistory(root);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void showBuyHistory(View root) {

        RecyclerView recyclerView = root.findViewById(R.id.rv_sell_history);
        SellHistoryAdapter adapter = new SellHistoryAdapter(getActivity(), sellHistoryModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}