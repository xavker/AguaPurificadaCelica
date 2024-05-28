package com.example.aguapurificadacelica.activities.Conocenos;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Conocenos.Adaptadores.Adaptador;
import com.example.aguapurificadacelica.activities.Conocenos.Colecciones.Caracteristicas;
import com.example.aguapurificadacelica.activities.Conocenos.Listener.IFirebaseLoadDone;
import com.example.aguapurificadacelica.activities.Home.Home;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Conocenos extends AppCompatActivity implements IFirebaseLoadDone {
    ViewPager viewPager;
    Adaptador adaptador;
    DatabaseReference mdatabase;
    List<Caracteristicas> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    IFirebaseLoadDone iFirebaseLoadDone;
    private CircleImageView mCircleImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conocenos);
        viewPager=findViewById(R.id.viewpageturismos);
        models = new ArrayList<>();
        mdatabase= FirebaseDatabase.getInstance().getReference("conocenos");
        iFirebaseLoadDone=this;
        mCircleImageBack = findViewById(R.id.circleImageBack);
        cargarDatos();
      //  viewPager.setPageTransformer(true,new DepthPageTransformer());
        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Conocenos.this, Home.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void cargarDatos() {
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Caracteristicas caracteristicas=new Caracteristicas();
                if(dataSnapshot.exists()){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        models.add(dataSnapshot1.getValue(Caracteristicas.class));
                        iFirebaseLoadDone.onFirebaseloadSuccesss(models);
                }else{
                    Toast.makeText(Conocenos.this,"NO EXTISTE ESTE DATO",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());

            }
        });
     //   models.add(new Caracteristicas(R.drawable.brochure, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
     //   models.add(new Caracteristicas(R.drawable.sticker, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
     //   models.add(new Caracteristicas(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
     //   models.add(new Caracteristicas(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));

    }

    @Override
    public void onFirebaseloadSuccesss(List<Caracteristicas> lugaresList) {
        adaptador = new Adaptador(this,models);
        viewPager.setAdapter(adaptador);
        viewPager.setPadding(130, 0, 130, 0);
        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adaptador.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFirebaseLoadFailed(String mensage) {
        Toast.makeText(Conocenos.this,mensage,Toast.LENGTH_LONG).show();

    }
}
