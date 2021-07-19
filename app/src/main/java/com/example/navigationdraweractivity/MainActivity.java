package com.example.navigationdraweractivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.navigationdraweractivity.ui.Cart.CartActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    ImageView imageView;
    TextView txtProfileName;
    TextView txtProfileEmail;
    TextView tvlogin;

    String Name;
    String Email;
    String Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        R.id.nav_price, R.id.nav_weather, R.id.nav_buy, R.id.nav_sell, R.id.nav_govScheme, R.id.nav_history, R.id.nav_contact, R.id.nav_service, R.id.nav_tandc, R.id.nav_language
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_price)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                if (destination.getId() == R.id.nav_logout){
//                    logout();
//                }
//            }
//        });

        View headerview = navigationView.getHeaderView(0);
        imageView = headerview.findViewById(R.id.imageView);
        txtProfileName = headerview.findViewById(R.id.txtProfileName);
        txtProfileEmail = headerview.findViewById(R.id.txtProfileEmail);

        FetchHeaderData();

        tvlogin = (TextView) headerview.findViewById(R.id.tv_login);
        tvlogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        imageView.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();

        });

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            //AppUtils.showLongToast("this works", getApplicationContext());
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//            Fragment selectedFragment;
//            selectedFragment = new PriceFragment();
//            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
            return true;
        });

    }

    @SuppressLint("SetTextI18n")
    private void FetchHeaderData() {
        
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            //tvlogin.setVisibility(View.VISIBLE);
            txtProfileName.setText("Krushi E Market");
            txtProfileEmail.setText("krushi@gmail.com");
            Glide.with(getApplicationContext()).load(R.drawable.tea).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);

        }else {
            //tvlogin.setVisibility(View.GONE);
            DocumentReference reference = FirebaseFirestore.getInstance().collection("Registration").document(user.getUid());
            reference.addSnapshotListener((value, error) -> {

                assert value != null;
                Name = value.getString("Fullname");
                Email = value.getString("Email");
                Profile = value.getString("ProfileUrl");

                txtProfileName.setText(Name);
                txtProfileEmail.setText(Email);

                Glide.with(getApplicationContext()).load(Profile).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);

            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_Cart);
        item.setOnMenuItemClickListener(item1 -> {

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
            return true;
        });
        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}