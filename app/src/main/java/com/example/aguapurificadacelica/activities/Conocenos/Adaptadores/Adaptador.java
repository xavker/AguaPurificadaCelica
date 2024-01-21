package com.example.aguapurificadacelica.activities.Conocenos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Conocenos.Colecciones.Caracteristicas;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends PagerAdapter {
    Context context;
    List<Caracteristicas> model;
    LayoutInflater layoutInflater;

    public Adaptador(Context context, List<Caracteristicas> model) {
        this.context = context;
        this.model = model;
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.cardviewturismo,container,false);
        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        //imageView.setImageResource(model.get(position).getImage());
        title.setText(model.get(position).getNombre());
        desc.setText(model.get(position).getDescripcion());
        Picasso.with(context).load(model.get(position).getImagen()).into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"Visita Celica", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, DetailActivityConocenos.class);
                //intent.putExtra("param", model.get(position).getNombre());
                //context.startActivity(intent);
                // finish();
            }
        });
        container.addView(view);
        return view;
    }
}
