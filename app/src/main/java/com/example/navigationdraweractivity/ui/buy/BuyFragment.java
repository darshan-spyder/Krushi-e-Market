package com.example.navigationdraweractivity.ui.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdraweractivity.LoginActivity;
import com.example.navigationdraweractivity.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class BuyFragment extends Fragment {

    RecyclerView recview;
    BuyAdapter adapter;

    //ArrayList<BuyModel> buyModelArrayList;
    //FirebaseDatabase dbr = FirebaseDatabase.getInstance();

//    String Crop_category;
//    String Crop_name;
//    String Crop_quantity;
//    String Crop_price;
//    String District;
//    String Uid;
//    String Taluka;
//    String Village;
//    String Crop_farmaddress;
//    String Other_details;
//    String Email;
//    String Fullname;
//    String Contact_no;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buy, container, false);

        //FirebaseUser UsersUid = FirebaseAuth.getInstance().getCurrentUser();
        //RecyclerView recyclerView = root.findViewById(R.id.buy_recycler_view);
        //buyModelArrayList = new ArrayList<>();

        setHasOptionsMenu(true);
        recview = root.findViewById(R.id.buy_recycler_view);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<BuyModel> options =
                new FirebaseRecyclerOptions.Builder<BuyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("SellDetails").orderByChild("Status").equalTo("Sell"), BuyModel.class)
                        .build();

        adapter=new BuyAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

        //FetchBuyDetail(root);

        return root;
    }

    /*private void FetchBuyDetail(View root) {
        FirebaseUser UsersUid = FirebaseAuth.getInstance().getCurrentUser();

        Query query = dbr.getReference().child("Users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {

                        Crop_category = childSnapshot.child("Crop_category").getValue().toString();
                        Crop_name = childSnapshot.child("Crop_name").getValue().toString();
                        Crop_quantity = childSnapshot.child("Crop_quantity").getValue().toString();
                        Crop_price = childSnapshot.child("Crop_price").getValue().toString();
                        District = childSnapshot.child("District").getValue().toString();
                        Taluka = childSnapshot.child("Taluka").getValue().toString();
                        Village = childSnapshot.child("Village").getValue().toString();
                        Crop_farmaddress = childSnapshot.child("Crop_farmaddress").getValue().toString();
                        Other_details = childSnapshot.child("Other_details").getValue().toString();

                        Uid = childSnapshot.child("Uid").getValue().toString();
                        Email = childSnapshot.child("Email").getValue().toString();
                        Fullname = childSnapshot.child("Fullname").getValue().toString();
                        Contact_no = childSnapshot.child("Contact_no").getValue().toString();
//                        buyModelArrayList.add();

                    }
                    showProList(root);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showProList(View root) {

//        RecyclerView recyclerView = root.findViewById(R.id.buy_recycler_view);
//        BuyAdapter adapter = new BuyAdapter(getActivity(), buyModelArrayList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }*/

    private void firebaseSearch(String searchText) {

        String query = searchText.toLowerCase();
//.orderByChild("Crop_price")
       // Query query1 = FirebaseDatabase.getInstance().getReference().child("SellDetails").orderByChild("Status").equalTo("Sell").orderByChild("Crop_name").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerOptions<BuyModel> options =
                new FirebaseRecyclerOptions.Builder<BuyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SellDetails").orderByChild("Crop_name").startAt(query).endAt(query + "\uf8ff"), BuyModel.class)
                        .build();

        adapter = new BuyAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater menuInflater) {

        menuInflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {

            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

}