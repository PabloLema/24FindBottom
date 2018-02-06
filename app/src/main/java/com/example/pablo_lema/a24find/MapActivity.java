package com.example.pablo_lema.a24find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String nombreLocal;
    private String latLocal;
    private String lngLocal;
    private int localId;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        localId = getIntent().getExtras().getInt("idLocal");
        nombreLocal = getIntent().getExtras().getString("nombreLocal");
        latLocal = getIntent().getExtras().getString("latLocal");
        lngLocal = getIntent().getExtras().getString("lngLocal");
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        LatLng place = new LatLng(Double.parseDouble(latLocal), Double.parseDouble(lngLocal));
        mMap.addMarker(new MarkerOptions().position(place).title(nombreLocal)).showInfoWindow();

//        acciones();

        float zoomLevel = 16.0f;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(place).zoom(zoomLevel).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        acciones();

    }

    public void acciones(){
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapActivity.this, SteeetViewActivity.class);
                intent.putExtra("latLocal", latLocal);
                intent.putExtra("lngLocal", lngLocal);
                startActivity(intent);
            }
        });

    }
}
