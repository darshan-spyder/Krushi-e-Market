package com.example.navigationdraweractivity.ui.buyhistory;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class BuyHistory extends Fragment {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String BuyKey;


    ArrayList<BuyHistoryModel> buyHistoryModelArrayList = new ArrayList<>();
    FirebaseDatabase dbr = FirebaseDatabase.getInstance();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buy_history, container, false);

        FetchBuyHistory(root);

        return root;

    }

    private void FetchBuyHistory(View root) {

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

            DatabaseReference reference = dbr.getReference("Users").child(UsersUid.getUid()).child("BuyHistory");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    buyHistoryModelArrayList.clear();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {

                        CropCategory = Objects.requireNonNull(childSnapshot.child("Crop_category").getValue()).toString();
                        CropName = Objects.requireNonNull(childSnapshot.child("Crop_name").getValue()).toString();
                        CropQuantity = Objects.requireNonNull(childSnapshot.child("Crop_quantity").getValue()).toString();
                        CropPrice = Objects.requireNonNull(childSnapshot.child("Crop_price").getValue()).toString();
                        BuyKey = childSnapshot.getKey();

                        buyHistoryModelArrayList.add(new BuyHistoryModel(CropCategory, CropName, CropQuantity, CropPrice, UsersUid.getUid(), BuyKey));
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

        RecyclerView recyclerView = root.findViewById(R.id.rvBuyHistory);
        BuyHistoryAdapter adapter = new BuyHistoryAdapter(getActivity(), buyHistoryModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}