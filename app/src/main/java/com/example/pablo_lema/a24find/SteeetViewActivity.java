package com.example.pablo_lema.a24find;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class SteeetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private String latLocal;
    private String lngLocal;

    private StreetViewPanorama stv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steeet_view);

        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        latLocal = getIntent().getExtras().getString("latLocal");
        lngLocal = getIntent().getExtras().getString("lngLocal");
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        stv = streetViewPanorama;
        stv.setPosition(new LatLng(Double.parseDouble(latLocal), Double.parseDouble(lngLocal)));
    }
}
