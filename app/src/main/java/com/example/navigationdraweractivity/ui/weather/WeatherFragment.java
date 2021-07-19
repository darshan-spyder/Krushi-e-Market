package com.example.navigationdraweractivity.ui.weather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.navigationdraweractivity.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WeatherFragment extends Fragment {

    final String APP_ID = "b6a3f80abda11138d63a629ba4091fc8";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final long MIN_TIME = 1000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String Location_Provider = LocationManager.GPS_PROVIDER;

    TextView NameofCity, weatherState, Temperature ,Humidity;
    ImageView mweatherIcon;

    LocationManager mLocationManager;
    LocationListener mLocationListener;

    EditText etcityname;
    ImageView searchcity;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weather, container, false);


        //final TextView textView = root.findViewById(R.id.tvhumidity);
        etcityname = root.findViewById(R.id.textField);
        searchcity = root.findViewById(R.id.searchCity);

        weatherState = root.findViewById(R.id.weatherCondition);
        Temperature = root.findViewById(R.id.temperature);
        mweatherIcon = root.findViewById(R.id.weatherIcon);
        Humidity = root.findViewById(R.id.humidity);
        NameofCity = root.findViewById(R.id.cityName);

        searchcity.setOnClickListener(v -> {
            String city =etcityname.getText().toString().trim();
            getWeatherforNewcity(city);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getWeatherforCurrentLocation();
    }

    private void getWeatherforCurrentLocation() {
        mLocationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());


                RequestParams params = new RequestParams();
                params.put("lat",Latitude);
                params.put("lon",Longitude);
                params.put("appid",APP_ID);
                letsdoSomeNetworking(params);


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }


        };
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getContext(), "Location get Succesfully", Toast.LENGTH_SHORT).show();
                getWeatherforCurrentLocation();
            }
            else
            {
                Toast.makeText(getContext(), "Location Not Found.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void letsdoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                Toast.makeText(getContext(), "DATA Get Success", Toast.LENGTH_SHORT).show();

                weatherData weatherD=weatherData.fromJson(response);
                assert weatherD != null;
                updateUI(weatherD);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(getContext(), "Please Refresh Page.", Toast.LENGTH_SHORT).show();
            }


        });
    }


    private void updateUI(weatherData weather)
    {
        Temperature.setText(weather.getmTemperature());
        NameofCity.setText(weather.getMcity());
        weatherState.setText(weather.getmWeatherType());
        Humidity.setText(weather.getMhumidity());
        //Picasso.get().load(weather.getMicon()).into(mweatherIcon);
        //Glide.with(this).asBitmap().load(weather.getMicon()).into(mweatherIcon);

        int resourcesID=getResources().getIdentifier(weather.getMicon(),"drawable", requireContext().getPackageName());
        mweatherIcon.setImageResource(resourcesID);
    }

    @Override
    public void onPause() {
        super.onPause();

        if(mLocationManager!=null)
        {
            mLocationManager.removeUpdates(mLocationListener);
        }

    }
    private void getWeatherforNewcity(String city){

        RequestParams params = new RequestParams();
        params.put("q",city);
        params.put("appid",APP_ID);
        letsdoSomeNetworking(params);
    }
}