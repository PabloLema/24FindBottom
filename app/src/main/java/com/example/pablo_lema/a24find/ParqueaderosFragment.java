package com.example.pablo_lema.a24find;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pablo_lema.a24find.Controller.AppController;
import com.example.pablo_lema.a24find.adapters.ParqueaderoArrayAdapter;
import com.example.pablo_lema.a24find.helper.RecyclerItemClickListener;
import com.example.pablo_lema.a24find.modelo.Imagenes;
import com.example.pablo_lema.a24find.modelo.Parqueadero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ParqueaderosFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String IP = "35.229.114.53";

    private List<Parqueadero> parqueaderos = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Vibrator vb;

    public ParqueaderosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parqueaderos, container, false);
        JSonRequest();
        inicializarComponentes(view);
        acciones();
        return view;
    }

    public static ParqueaderosFragment newInstance() {
        ParqueaderosFragment fragment = new ParqueaderosFragment();
        return fragment;
    }

    public void inicializarComponentes(View view) {
        adapter = new ParqueaderoArrayAdapter(parqueaderos);
        recyclerView = view.findViewById(R.id.recyclerParqueaderos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void JSonRequest() {
        String URL = "http://" + IP + ":80/24/srv/24find/parqueaderos";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Cargando...");
        pDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject parqueaderoObject = response.getJSONObject(i);
                        Parqueadero parqueadero = new Parqueadero();

                        parqueadero.setId(parqueaderoObject.getInt("id"));
                        parqueadero.setNombre(parqueaderoObject.getString("nombre"));
                        parqueadero.setDireccion(parqueaderoObject.getString("direccion"));
                        parqueadero.setPuestosDisponibles(parqueaderoObject.getInt("puestosDisponibles"));
                        parqueadero.setTarifa(parqueaderoObject.getDouble("tarifa"));
                        parqueadero.setLat(parqueaderoObject.getString("lat"));
                        parqueadero.setLng(parqueaderoObject.getString("lng"));
                        parqueadero.setValoracion(parqueaderoObject.getInt("valoracion"));


                        JSONArray imagenesJsonArray = parqueaderoObject.getJSONArray("imagenes");
                        List<Imagenes> imagenesList = new ArrayList<>();

                        for (int k = 0; k < imagenesJsonArray.length(); k++) {
                            JSONObject imagenesObject = (JSONObject) imagenesJsonArray.get(k);
                            Imagenes imagenes = new Imagenes();

                            imagenes.setUrlImagen(imagenesObject.getString("urlImagen"));

                            imagenesList.add(imagenes);
                            parqueadero.setImagenesListP(imagenesList);
                        }

                        parqueaderos.add(parqueadero);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                pDialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                VolleyLog.d("", "");
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    public void acciones() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(view.getContext(), "longClick", Toast.LENGTH_LONG).show();
//                vb.vibrate(10);
//                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//                popupMenu.inflate(R.menu.popup_menu);
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.itmAgregarFavoritos:
//                                Toast.makeText(getContext(), "Agregado a favoritos", Toast.LENGTH_LONG).show();
//                                break;
//
//                        }
//                        return false;
//                    }
//                });
//                @SuppressLint("RestrictedApi") MenuPopupHelper menuPopupHelper = new MenuPopupHelper(view.getContext(), (MenuBuilder) popupMenu.getMenu(), view);
//                menuPopupHelper.setForceShowIcon(true);
//                menuPopupHelper.show();

            }
        }));
    }

}
