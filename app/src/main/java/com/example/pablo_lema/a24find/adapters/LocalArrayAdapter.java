package com.example.pablo_lema.a24find.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.pablo_lema.a24find.Controller.AppController;
import com.example.pablo_lema.a24find.R;
import com.example.pablo_lema.a24find.modelo.Local;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo_lema on 27/1/18.
 */

public class LocalArrayAdapter extends RecyclerView.Adapter<LocalArrayAdapter.ViewHolder> {

    private List<Local> locales = new ArrayList<>();
    private String horarios = "";
    private ImageLoader imageLoader;

    public LocalArrayAdapter(List<Local> locales) {
        this.locales = locales;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_locales, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvtNombreLocal.setText(locales.get(position).getNombre());
        holder.tvtDireccionLocal.setText(locales.get(position).getDireccion());
        holder.tvtDescripcionLocal.setText(locales.get(position).getDescripcion());


        for (int i = 0; i < locales.get(position).getHorariosAtencion().size(); i++) {
            horarios += locales.get(position).getHorariosAtencion().get(i).getDias() + "\n" + locales.get(position).getHorariosAtencion().get(i).getHoraApertura() + " - " + locales.get(position).getHorariosAtencion().get(i).getHoraCierre() + "\n";
        }

        holder.tvtHorariosLocal.setText(horarios);
        horarios = "";

        imageLoader = AppController.getInstance().getImageLoader();
        Local l = locales.get(position);
        holder.networkImageView.setImageUrl(l.getImagenesList().get(0).getUrlImagen(), imageLoader);

        holder.ratingLocales.setRating((float) locales.get(position).getPromedioCalificaciones());
    }

    @Override
    public int getItemCount() {
        return locales.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvtNombreLocal;
        protected TextView tvtDireccionLocal;
        protected TextView tvtDescripcionLocal;
        protected TextView tvtHorariosLocal;
        protected NetworkImageView networkImageView;
        protected RatingBar ratingLocales;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtNombreLocal = itemView.findViewById(R.id.tvtNombreLocal);
            tvtDireccionLocal = itemView.findViewById(R.id.tvtDireccionLocal);
            tvtDescripcionLocal = itemView.findViewById(R.id.tvtDescripcionLocal);
            tvtHorariosLocal = itemView.findViewById(R.id.tvtHorarioLocal);
            networkImageView = itemView.findViewById(R.id.imgURL);
            ratingLocales = itemView.findViewById(R.id.ratingLocal);
        }
    }
}

