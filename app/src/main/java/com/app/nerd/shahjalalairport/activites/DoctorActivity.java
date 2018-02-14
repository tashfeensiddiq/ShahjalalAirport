package com.app.nerd.shahjalalairport.activites;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.nerd.shahjalalairport.AppController;
import com.app.nerd.shahjalalairport.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.app.nerd.shahjalalairport.app.AppConfig.GEOMETRY;
import static com.app.nerd.shahjalalairport.app.AppConfig.GOOGLE_BROWSER_API_KEY;
import static com.app.nerd.shahjalalairport.app.AppConfig.ICON;
import static com.app.nerd.shahjalalairport.app.AppConfig.LATITUDE;
import static com.app.nerd.shahjalalairport.app.AppConfig.LOCATION;
import static com.app.nerd.shahjalalairport.app.AppConfig.LONGITUDE;
import static com.app.nerd.shahjalalairport.app.AppConfig.NAME;
import static com.app.nerd.shahjalalairport.app.AppConfig.OK;
import static com.app.nerd.shahjalalairport.app.AppConfig.PLACE_ID;
import static com.app.nerd.shahjalalairport.app.AppConfig.PROXIMITY_RADIUS;
import static com.app.nerd.shahjalalairport.app.AppConfig.REFERENCE;
import static com.app.nerd.shahjalalairport.app.AppConfig.STATUS;
import static com.app.nerd.shahjalalairport.app.AppConfig.ID;
import static com.app.nerd.shahjalalairport.app.AppConfig.TAG;
import static com.app.nerd.shahjalalairport.app.AppConfig.VICINITY;
import static com.app.nerd.shahjalalairport.app.AppConfig.ZERO_RESULTS;

public class DoctorActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setIndoorEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng airport = new LatLng(23.8466, 90.4026);
        mMap.addMarker(new MarkerOptions().position(airport).title("Shahjalal International Airport"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        loadNearByPlaces(airport.latitude,airport.longitude);
    }

    private void loadNearByPlaces(double latitude, double longitude) {
//YOU Can change this type at your own will, e.g hospital, cafe, restaurant.... and see how it all works

        String type = "doctor";
        String googlePlacesUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                + "location="
                + latitude
                + ","
                + longitude +
                "&radius=" + PROXIMITY_RADIUS +
                "&types=" + type +
                "&sensor=true" +
                "&key=" + GOOGLE_BROWSER_API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(googlePlacesUrl,

                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject result) {
                        Log.i("Airpot", "onResponse: Result= " + result.toString());
                        parseLocationResult(result);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Airport", "onErrorResponse: Error= " + error);
                        Log.e("Airport", "onErrorResponse: Error= " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(request);
    }

    private void parseLocationResult(JSONObject result) {

        String id, place_id, placeName = null, reference, icon, vicinity = null;
        double latitude, longitude;

        try {
            JSONArray jsonArray = result.getJSONArray("results");

            if (result.getString(STATUS).equalsIgnoreCase(OK)) {

                mMap.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject place = jsonArray.getJSONObject(i);

                    id = place.getString(ID);
                    place_id = place.getString(PLACE_ID);
                    if (!place.isNull(NAME)) {
                        placeName = place.getString(NAME);
                    }
                    if (!place.isNull(VICINITY)) {
                        vicinity = place.getString(VICINITY);
                    }
                    latitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)

                            .getDouble(LATITUDE);
                    longitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)

                            .getDouble(LONGITUDE);
                    reference = place.getString(REFERENCE);
                    icon = place.getString(ICON);

                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(placeName + " : " + vicinity);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                    mMap.addMarker(markerOptions);
                }

                Toast.makeText(this, jsonArray.length() + " Doctor found!",

                        Toast.LENGTH_LONG).show();
            } else if (result.getString(STATUS).equalsIgnoreCase(ZERO_RESULTS)) {
                Toast.makeText(this, "No Hotels found in 5KM radius!!!",

                        Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            e.printStackTrace();
            Log.e(TAG, "parseLocationResult: Error=" + e.getMessage());
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DoctorActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
        finish();
    }
}
