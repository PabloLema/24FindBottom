package com.example.pablo_lema.a24find;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pablo_lema.a24find.Controller.AppController;
import com.example.pablo_lema.a24find.adapters.LocalArrayAdapter;
import com.example.pablo_lema.a24find.helper.RecyclerItemClickListener;
import com.example.pablo_lema.a24find.modelo.Critica;
import com.example.pablo_lema.a24find.modelo.HorariosAtencion;
import com.example.pablo_lema.a24find.modelo.Imagenes;
import com.example.pablo_lema.a24find.modelo.Local;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalesFragment extends Fragment
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private String IP = "35.229.114.53";

    private List<Local> locales = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Vibrator vb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locales, container, false);
        JSonRequest();
        inicializarComponentes(view);
        acciones();
        return view;
    }

    public static LocalesFragment newInstance() {
        LocalesFragment fragment = new LocalesFragment();
        return fragment;
    }

    public void inicializarComponentes(View view) {

        vb = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        adapter = new LocalArrayAdapter(locales);
        recyclerView = view.findViewById(R.id.recyclerLocales);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void JSonRequest() {
        String URL = "http://" + IP + ":80/24/srv/24find/locales";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Cargando...");
        pDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject localObject = response.getJSONObject(i);
                        Local local = new Local();

                        local.setId(localObject.getInt("id"));
                        local.setNombre(localObject.getString("nombre"));
                        local.setDireccion(localObject.getString("direccion"));
                        local.setDescripcion(localObject.getString("descripcion"));

                        JSONArray horariosJsonArray = localObject.getJSONArray("horariosAtencion");
                        List<HorariosAtencion> horariosAtencionList = new ArrayList<>();

                        for (int j = 0; j < horariosJsonArray.length(); j++) {
                            JSONObject horariosObject = (JSONObject) horariosJsonArray.get(j);
                            HorariosAtencion horariosAtencion = new HorariosAtencion();

                            horariosAtencion.setDias(horariosObject.getString("dias"));
                            horariosAtencion.setHoraApertura(horariosObject.getString("horaApertura"));
                            horariosAtencion.setHoraCierre(horariosObject.getString("horaCierre"));

                            horariosAtencionList.add(horariosAtencion);
                            local.setHorariosAtencion(horariosAtencionList);
                        }

                        JSONArray imagenesJsonArray = localObject.getJSONArray("imagenesLocal");
                        List<Imagenes> imagenesList = new ArrayList<>();

                        for (int k = 0; k < imagenesJsonArray.length(); k++) {
                            JSONObject imagenesObject = (JSONObject) imagenesJsonArray.get(k);
                            Imagenes imagenes = new Imagenes();

                            imagenes.setUrlImagen(imagenesObject.getString("urlImagen"));

                            imagenesList.add(imagenes);
                            local.setImagenesList(imagenesList);
                        }

                        JSONArray criticaJsonArray = localObject.getJSONArray("criticas");
                        List<Critica> criticaList = new ArrayList<>();
                        double suma = 0;
                        for (int j = 0; j < criticaJsonArray.length(); j++) {
                            JSONObject criticasObject = (JSONObject) criticaJsonArray.get(j);
                            Critica critica = new Critica();

                            critica.setValoracion(criticasObject.getInt("valoracion"));

                            criticaList.add(critica);
                            local.setCriticaList(criticaList);

                            suma += critica.getValoracion();

                        }
                        local.setPromedioCalificaciones(calcularProemdioCalificaciones(suma, criticaJsonArray.length()));
                        locales.add(local);

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
                Intent intent = new Intent(view.getContext(), LocalDetalleActivity.class);
                intent.putExtra("localId", locales.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                vb.vibrate(10);
                new AlertDialog.Builder(view.getContext()).
                        setTitle(locales.get(position).getNombre()).
                        setMessage("Agregar a favoritos").
                        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), locales.get(position).getNombre() + " agregado a favoritos", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        }));
    }

    public double calcularProemdioCalificaciones(double Suma, double Total){
        return Suma / Total;
    }
}
