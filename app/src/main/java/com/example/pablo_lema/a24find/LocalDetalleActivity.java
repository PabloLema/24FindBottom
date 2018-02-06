package com.example.pablo_lema.a24find;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pablo_lema.a24find.Controller.AppController;
import com.example.pablo_lema.a24find.adapters.ImageAdapter;
import com.example.pablo_lema.a24find.modelo.Critica;
import com.example.pablo_lema.a24find.modelo.HorariosAtencion;
import com.example.pablo_lema.a24find.modelo.Imagenes;
import com.example.pablo_lema.a24find.modelo.Local;
import com.example.pablo_lema.a24find.modelo.Telefono;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class LocalDetalleActivity extends AppCompatActivity {

    private int idLocal;
    private String IP = "35.229.114.53";
    private Local local;

    private ViewPager viewPager;
    private ArrayList<String> imagenesL;

    private TextView tvtNombreL;
    private TextView tvtDireccionL;
    private TextView tvtDescripcionL;
    private TextView tvtHorariosAtencionL;

    private Button btnMostarEnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detalle);

        idLocal = getIntent().getExtras().getInt("localId");
        jsonRequest();

    }

    public void jsonRequest() {
        String URL = "http://" + IP + ":80/24/srv/24find/leerlocal?id=" + String.valueOf(idLocal);
//        final ProgressDialog pDialog = new ProgressDialog(getApplication());
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Cargando...");
//        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    local = new Local();
                    imagenesL = new ArrayList<>();
                    local.setId(response.getInt("id"));
                    local.setNombre(response.getString("nombre"));
                    local.setDescripcion(response.getString("descripcion"));
                    local.setDireccion(response.getString("direccion"));
                    local.setLat(response.getString("lat"));
                    local.setLng(response.getString("lng"));

                    JSONArray telefonoJsonArray = response.getJSONArray("telefonosLocal");
                    List<Telefono> telefonoList = new ArrayList<>();
                    for (int i = 0; i < telefonoJsonArray.length(); i++) {
                        JSONObject telefonosObject = (JSONObject) telefonoJsonArray.get(i);
                        Telefono telefono = new Telefono();

                        telefono.setId(telefonosObject.getInt("id"));
                        telefono.setNumeroTelefono(telefonosObject.getString("numeroTelefono"));
                        telefono.setTipo(telefonosObject.getString("tipo"));

                        telefonoList.add(telefono);
                        local.setTelefonoList(telefonoList);
                    }

                    JSONArray criticaJsonArray = response.getJSONArray("criticas");
                    List<Critica> criticaList = new ArrayList<>();
                    for (int j = 0; j < criticaJsonArray.length(); j++) {
                        JSONObject criticasObject = (JSONObject) criticaJsonArray.get(j);
                        Critica critica = new Critica();

                        critica.setId(criticasObject.getInt("id"));
                        critica.setComentario(criticasObject.getString("comentario"));
                        critica.setValoracion(criticasObject.getInt("valoracion"));
                        critica.setFechaActual(criticasObject.getString("fechaActual"));

                        criticaList.add(critica);
                        local.setCriticaList(criticaList);
                    }

                    JSONArray horariosJsonArray = response.getJSONArray("horariosAtencion");
                    List<HorariosAtencion> horariosAtencionList = new ArrayList<>();
                    for (int k = 0; k < horariosJsonArray.length(); k++) {
                        JSONObject horariosObject = (JSONObject) horariosJsonArray.get(k);
                        HorariosAtencion horariosAtencion = new HorariosAtencion();

                        horariosAtencion.setId(horariosObject.getInt("id"));
                        horariosAtencion.setDias(horariosObject.getString("dias"));
                        horariosAtencion.setHoraApertura(horariosObject.getString("horaApertura"));
                        horariosAtencion.setHoraCierre(horariosObject.getString("horaCierre"));

                        horariosAtencionList.add(horariosAtencion);
                        local.setHorariosAtencion(horariosAtencionList);
                    }

                    JSONArray imagenesJsonArray = response.getJSONArray("imagenesLocal");
                    List<Imagenes> imagenesList = new ArrayList<>();
                    for (int l = 0; l < imagenesJsonArray.length(); l++) {
                        JSONObject imagenesObject = (JSONObject) imagenesJsonArray.get(l);
                        Imagenes imagenes = new Imagenes();

                        imagenes.setId(imagenesObject.getInt("id"));
                        imagenes.setUrlImagen(imagenesObject.getString("urlImagen"));

                        imagenesList.add(imagenes);


                        imagenesL.add(imagenesObject.getString("urlImagen"));
                        local.setImagenesList(imagenesList);
                    }

                    inicializarComponentes();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                pDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                pDialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void inicializarComponentes() {
        viewPager = findViewById(R.id.vPager);
        CircleIndicator indicator = findViewById(R.id.cIndicator);
        viewPager.setAdapter(new ImageAdapter(LocalDetalleActivity.this, imagenesL));
        indicator.setViewPager(viewPager);

        tvtNombreL = findViewById(R.id.tvtNombreL);
        tvtNombreL.setText(local.getNombre());

        tvtDescripcionL = findViewById(R.id.tvtDescripcionL);
        tvtDescripcionL.setText(local.getDescripcion());

        tvtDireccionL = findViewById(R.id.tvtDireccionL);
        tvtDireccionL.setText(local.getDireccion());

        tvtHorariosAtencionL = findViewById(R.id.tvtHorariosL);
        String horariosAtencion = "";
        for (int i = 0; i < local.getHorariosAtencion().size(); i++) {
            horariosAtencion += local.getHorariosAtencion().get(i).getDias() + "\n" + local.getHorariosAtencion().get(i).getHoraApertura() + "\n" + local.getHorariosAtencion().get(i).getHoraCierre() + "\n";
        }
        tvtHorariosAtencionL.setText(horariosAtencion);

        btnMostarEnMapa = findViewById(R.id.btnMostrarMapaL);

        acciones();


    }

    public void acciones(){

        btnMostarEnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                intent.putExtra("nombreLocal", local.getNombre());
                intent.putExtra("latLocal", local.getLat());
                intent.putExtra("lngLocal", local.getLng());
                intent.putExtra("idLocal", local.getId());
                startActivity(intent);
            }
        });

    }
}
