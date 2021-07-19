package com.example.navigationdraweractivity.ui.sell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.navigationdraweractivity.LoginActivity;
import com.example.navigationdraweractivity.MainActivity;
import com.example.navigationdraweractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;


public class SellFragment extends Fragment {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Spinner category, name, district, taluka, village;
    private String Crop_Quentity, Crop_price, Crop_farmaddress, Crop_category, Crop_name, District, Taluka, Village, OtherDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sell, container, false);

        category = root.findViewById(R.id.r_s_selectcategory);
        name = root.findViewById(R.id.r_s_selectname);
        district = root.findViewById(R.id.r_s_selectdistrict);
        taluka = root.findViewById(R.id.r_s_selecttaluka);
        village = root.findViewById(R.id.r_s_selectvillage);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = String.valueOf(category.getSelectedItem());

                if (select.equals("Cereals")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.cereals_list));
                    name.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("Pulses")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.pulses_list));
                    name.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("Spices")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.spices_list));
                    name.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("CashCrops")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.cash_crops_list));
                    name.setAdapter(spinnerArrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = String.valueOf(district.getSelectedItem());

                if (select.equals("Junagadh")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.junagadh_Taluka));
                    taluka.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("Other")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.other_Taluka));
                    taluka.setAdapter(spinnerArrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        taluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = String.valueOf(taluka.getSelectedItem());

                if (select.equals("junagadh")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.junagadh_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("vanthali")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.vanthali_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("manavadar")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.manavadar_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("keshod")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.keshod_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("mangrol")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.mangrol_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("mendarda")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.mendarda_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("maliya hatina")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.maliyahatina_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("bhesan")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.bhesan_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("visavadar")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.visavadar_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
                if (select.equals("other1")) {
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item, getResources().getStringArray(R.array.other1_Village));
                    village.setAdapter(spinnerArrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button adddata = root.findViewById(R.id.r_b_submit);
        adddata.setOnClickListener(view -> {

            EditText etcrop_quentity = root.findViewById(R.id.r_etcrop_quentity);
            Crop_Quentity = etcrop_quentity.getText().toString();
            EditText etcrop_price = root.findViewById(R.id.r_etcrop_price);
            Crop_price = etcrop_price.getText().toString();
            EditText etcrop_farmaddress = root.findViewById(R.id.r_etcrop_farmaddress);
            Crop_farmaddress = etcrop_farmaddress.getText().toString();
            EditText etotherdetails = root.findViewById(R.id.r_etotherdetails);
            OtherDetails = etotherdetails.getText().toString();

            category = root.findViewById(R.id.r_s_selectcategory);
            Crop_category = String.valueOf(category.getSelectedItem());
            name = root.findViewById(R.id.r_s_selectname);
            Crop_name = String.valueOf(name.getSelectedItem());
            district = root.findViewById(R.id.r_s_selectdistrict);
            District = String.valueOf(district.getSelectedItem());
            taluka = root.findViewById(R.id.r_s_selecttaluka);
            Taluka = String.valueOf(taluka.getSelectedItem());
            village = root.findViewById(R.id.r_s_selectvillage);
            Village = String.valueOf(village.getSelectedItem());

            if (Crop_Quentity.isEmpty()) {
                etcrop_quentity.setError("Enter Valid Date.");
                etcrop_quentity.requestFocus();
            } else if (etcrop_price.getText().toString().isEmpty()) {
                etcrop_price.setError("Enter Valid Month.");
                etcrop_price.requestFocus();
            } else if (etcrop_farmaddress.getText().toString().isEmpty()) {
                etcrop_farmaddress.setError("Enter Valid Year.");
                etcrop_farmaddress.requestFocus();
            } else if (etotherdetails.getText().toString().isEmpty()) {
                etotherdetails.setError("Enter Proper Year.");
                etotherdetails.requestFocus();
            } else if (category.toString().equals("Select")) {
                Toast.makeText(getContext(), "Select Proper Event Name.", Toast.LENGTH_LONG).show();
            } else if (name.toString().equals("Select")) {
                Toast.makeText(getContext(), "Select Proper Event Name.", Toast.LENGTH_LONG).show();
            } else if (district.toString().equals("Select")) {
                Toast.makeText(getContext(), "Select Proper Event Name.", Toast.LENGTH_LONG).show();
            } else if (taluka.toString().equals("Select")) {
                Toast.makeText(getContext(), "Select Proper Event Name.", Toast.LENGTH_LONG).show();
            } else if (village.toString().equals("Select")) {
                Toast.makeText(getContext(), "Select Proper Event Name.", Toast.LENGTH_LONG).show();
            } else {
                SendData();
                //requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HistoryFragment()).commitNowAllowingStateLoss();
                //Toast.makeText(getContext(), "Your data is inserted.", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    private void SendData() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Data Uploading!!!");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

//        Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        reference = FirebaseDatabase.getInstance().getReference("SellDetails").push();
        //reference = FirebaseDatabase.getInstance().getReference().child("SellDetails");

        String key = reference.getKey();

        Map<String, Object> data = new HashMap<>();
        data.put("Crop_category", Crop_category);
        data.put("Crop_farmaddress", Crop_farmaddress);
        data.put("Crop_name", Crop_name);
        data.put("Crop_price", Crop_price);
        data.put("Crop_quantity", Crop_Quentity);
        data.put("Other_details", OtherDetails);
        data.put("District", District);
        data.put("Taluka", Taluka);
        data.put("Village", Village);
        data.put("Uid", user.getUid());
        data.put("Status", "Sell");
        data.put("Key", key);

        reference.setValue(data);

        //reference1.push().setValue(data);

//        Map<String, Object> data1 = new HashMap<>();
//        data1.put("Crop_category", Crop_category);
//        data1.put("Crop_farmaddress", Crop_farmaddress);
//        data1.put("Crop_name", Crop_name);
//        data1.put("Crop_price", Crop_price);
//        data1.put("Crop_quantity", Crop_Quentity);
//        data1.put("Other_details", OtherDetails);
//        data1.put("District", District);
//        data1.put("Taluka", Taluka);
//        data1.put("Village", Village);


        //Snackbar.make(findViewById(android.R.id.content), "Registration Successfully!!!", Snackbar.LENGTH_LONG).show();
        progressDialog.dismiss();

        Toast.makeText(getContext(), "Data Set.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish();
        //Fragment selectedFragment;
        ///selectedFragment = new HistoryFragment();
        //getActivity().getSupportFragmentManager().beginTransaction().
//                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HistoryFragment()).commitNowAllowingStateLoss();

        //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PresenceLogFragment()).commit();
        //Intent intent = new Intent(getContext(), HistoryFragment.class);
        //startActivity(intent);
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