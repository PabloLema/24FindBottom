package com.example.pablo_lema.a24find;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.pablo_lema.a24find.helper.BottomNavigationViewBehavior;
import com.example.pablo_lema.a24find.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity implements FavoritosFragment.OnFragmentInteractionListenerF, PromocionesFragment.OnFragmentInteractionListenerPr {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_locales:
                    fragment = LocalesFragment.newInstance();
                    break;
                case R.id.navigation_parqueaderos:
                    fragment = ParqueaderosFragment.newInstance();
                    break;
                case R.id.navigation_promociones:
                    fragment = PromocionesFragment.newInstance("Fragment", "Promociones");
                    break;
                case R.id.navigation_favoritos:
                    fragment = FavoritosFragment.newInstance("Fragment", "Favoritos");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragmentDefault();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());


    }

    @Override
    public void onFragmentInteractionF(Uri uri) {

    }

    @Override
    public void onFragmentInteractionPr(Uri uri) {

    }

    public void loadFragmentDefault() {
        Fragment fragment = LocalesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }
}
