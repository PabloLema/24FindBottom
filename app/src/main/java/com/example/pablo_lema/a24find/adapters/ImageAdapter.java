package com.example.pablo_lema.a24find.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pablo_lema.a24find.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pablo_lema on 2/1/18.
 */

public class ImageAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private LayoutInflater layoutInflater;
    private Context context;

    public ImageAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.view_pager_images, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        container.addView(view, 0);
        Picasso.with(context)
                .load(images.get(position))
                .into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
