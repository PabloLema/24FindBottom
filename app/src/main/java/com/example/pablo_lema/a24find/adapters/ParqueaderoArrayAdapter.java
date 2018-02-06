package com.example.pablo_lema.a24find.adapters;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.pablo_lema.a24find.MapActivity;
import com.example.pablo_lema.a24find.R;
import com.example.pablo_lema.a24find.modelo.Parqueadero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo_lema on 1/2/18.
 */

public class ParqueaderoArrayAdapter extends RecyclerView.Adapter<ParqueaderoArrayAdapter.ViewHolder> {

    private List<Parqueadero> parqueaderos = new ArrayList<>();
    private ImageLoader imageLoader;

    public ParqueaderoArrayAdapter(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_parqueaderos, parent, false);
        ParqueaderoArrayAdapter.ViewHolder viewHolder = new ParqueaderoArrayAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvtNombreParqueadero.setText(parqueaderos.get(position).getNombre());
        holder.tvtDireccionParqueadero.setText(parqueaderos.get(position).getDireccion());
        holder.tvtPuestosDisponiblesParqueadero.setText(String.valueOf(parqueaderos.get(position).getPuestosDisponibles()) + " Puestos Disponibles");
        holder.ratingParqueaderos.setRating((float) parqueaderos.get(position).getValoracion());
        holder.tvtTarifaParqueadero.setText(String.valueOf(parqueaderos.get(position).getTarifa()) + "$ La Hora");

        holder.btnMenuParqueadero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itmAgregarFavoritos:
                                new AlertDialog.Builder(view.getContext()).
                                        setTitle(parqueaderos.get(position).getNombre()).
                                        setMessage("Agregar a favoritos").
                                        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(view.getContext(), parqueaderos.get(position).getNombre() + " agregado a favoritos", Toast.LENGTH_LONG).show();
                                            }
                                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                                break;

                            case R.id.itmMostrarMapa:
                                Intent intent = new Intent(view.getContext(), MapActivity.class);
                                intent.putExtra("nombreLocal", parqueaderos.get(position).getNombre());
                                intent.putExtra("latLocal", parqueaderos.get(position).getLat());
                                intent.putExtra("lngLocal", parqueaderos.get(position).getLng());
                                intent.putExtra("idLocal", parqueaderos.get(position).getId());
                                view.getContext().startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                @SuppressLint("RestrictedApi") MenuPopupHelper menuPopupHelper = new MenuPopupHelper(view.getContext(), (MenuBuilder) popupMenu.getMenu(), view);
                menuPopupHelper.setForceShowIcon(true);
                menuPopupHelper.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return parqueaderos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvtNombreParqueadero;
        protected TextView tvtPuestosDisponiblesParqueadero;
        protected TextView tvtDireccionParqueadero;
        protected TextView tvtTarifaParqueadero;
        protected NetworkImageView networkImageViewP;
        protected RatingBar ratingParqueaderos;
        protected ImageButton btnMenuParqueadero;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtNombreParqueadero = itemView.findViewById(R.id.tvtNombreParqueadero);
            tvtPuestosDisponiblesParqueadero = itemView.findViewById(R.id.tvtPuestosParqueadero);
            tvtDireccionParqueadero = itemView.findViewById(R.id.tvtDireccionParqueadero);
            tvtTarifaParqueadero = itemView.findViewById(R.id.tvtTarifaParqueadero);
            networkImageViewP = itemView.findViewById(R.id.imgURLParqueadero);
            ratingParqueaderos = itemView.findViewById(R.id.ratingParqueadero);
            btnMenuParqueadero = itemView.findViewById(R.id.btnMenuParqueadero);
        }
    }

}
