package com.example.navigationdraweractivity.ui.price;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.navigationdraweractivity.MainActivity;
import com.example.navigationdraweractivity.R;
import com.example.navigationdraweractivity.ui.price.Adapter.MyItemGroupAdapter;
import com.example.navigationdraweractivity.ui.price.Model.ItemData;
import com.example.navigationdraweractivity.ui.price.Model.ItemGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;

public class PriceFragment extends Fragment {

    // creating object of ViewPager
//    ViewPager mViewPager;

    // images array
    //int[] images = {R.drawable.download, R.drawable.homepage, R.drawable.profile, R.drawable.weather};

//     Creating Object of ViewPagerAdapter
//    ViewPagerAdapter mViewPagerAdapter;

    ViewFlipper v_flipper;

    AlertDialog dialog;
    PriceFragment iFirebaseLoadListener;

    RecyclerView my_recycle_view;

    DatabaseReference myData;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_price, container, false);
        //((RegistrationActivity) getActivity()).FragmentMethod();
//view flipper start, R.drawable.farmad3, R.drawable.farmad4
        int[] images = {R.drawable.f1, R.drawable.f2,R.drawable.f3,R.drawable.f4};
        v_flipper = root.findViewById(R.id.v_flipper);
        for (int value : images) {
            flipperImages(value);
        }
        for(int image: images){
            flipperImages(image);
        }
//        view flipper end

//        // Initializing the ViewPager Object
        //mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        //mViewPagerAdapter = new ViewPagerAdapter(MainActivity.this, images);

        // Adding the Adapter to the ViewPager
        //mViewPager.setAdapter(mViewPagerAdapter);

        myData = FirebaseDatabase.getInstance().getReference("MyData");
        dialog = new SpotsDialog.Builder().setContext(getContext()).build();
        iFirebaseLoadListener = this;

        my_recycle_view = root.findViewById(R.id.my_recycler_view);
        my_recycle_view.setHasFixedSize(true);
        my_recycle_view.setLayoutManager(new LinearLayoutManager(getContext()));


        getFirebaseData();
        return root;
    }

    //view flipper start
    public void flipperImages(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
//    view flipper end

    private void getFirebaseData() {
        dialog.show();

        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemGroup> itemGroups = new ArrayList<>();
                for (DataSnapshot groupSnapShot:dataSnapshot.getChildren())
                {
                    ItemGroup itemGroup = new ItemGroup();
                    itemGroup.setHeaderTitle(Objects.requireNonNull(groupSnapShot.child("headerTitle").getValue(true)).toString());
                    //itemGroup.setHeaderTitle(groupSnapShot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<ItemData>>(){};

                    itemGroup.setListItem(groupSnapShot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);
                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseLoadListener.onFirebaseLoadfailled(error.getMessage());

            }
        });
    }



    public void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList){
        MyItemGroupAdapter adapter = new MyItemGroupAdapter(getContext(),itemGroupList);
        my_recycle_view.setAdapter(adapter);

        dialog.dismiss();

    }

    public void onFirebaseLoadfailled(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}