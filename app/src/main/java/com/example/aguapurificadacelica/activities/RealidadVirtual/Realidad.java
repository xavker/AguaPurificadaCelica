package com.example.aguapurificadacelica.activities.RealidadVirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.aguapurificadacelica.R;

import com.example.aguapurificadacelica.activities.Home.Home;

import com.example.aguapurificadacelica.activities.providers.ConfigProvaider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class Realidad extends AppCompatActivity {
    private Button mButtonbotella;
    private Button mButtonetiqueta;
    private Button mButtonlogo;
    private ConfigProvaider configProvaider;

    private  Boolean deliveryaux;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realidad);
        mButtonbotella=findViewById(R.id.btnbotella);
        mButtonetiqueta=findViewById(R.id.btnetiqueta);
        mButtonlogo=findViewById(R.id.btnLogo);
        deliveryaux=false;
        configProvaider=new ConfigProvaider();

        final Animation animation1= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        final Animation animation2= AnimationUtils.loadAnimation(this,R.anim.anim_translate2);
        final Animation animation3= AnimationUtils.loadAnimation(this,R.anim.anim_translate3);
        String urlId="https://www.facebook.com/fbcameraeffects/testit/549183790622055/MTQ1ZjMwZjY5YTBjNjk5MjdkZDM0M2Y1YjI4NDAxMDA=/?source=test_link";

        String urlbotella="https://www.facebook.com/fbcameraeffects/tryit/1147739776386502/";
        View v=new View(this);
        v.startAnimation(animation1);
        v.startAnimation(animation2);
        v.startAnimation(animation3);
        mButtonbotella.setAnimation(animation1);
        mButtonetiqueta.setAnimation(animation2);
        mButtonlogo.setAnimation(animation3);
        deliveryaux=deliveryOn();
        mButtonbotella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
               try{
                   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbotella)));
               }catch (ActivityNotFoundException e){
                   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbotella)));

               }

            }
        });
        mButtonetiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));

                }
            }
        });
        mButtonlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));

                }
            }
        });




    }
    private Boolean deliveryOn(){
        configProvaider.getConfig("delivery").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String delivery=dataSnapshot.getValue().toString();
                    if(delivery.equals("1")){
                        deliveryaux=true;
                        //Toast.makeText(Home.this,"Abriendo entregas",Toast.LENGTH_LONG).show();
                    }else{
                        deliveryaux=false;
                        //Toast.makeText(Home.this,"Servicio cerrado por el momento",Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Toast.makeText(Home.this,"delivery:"+String.valueOf(deliveryaux),Toast.LENGTH_LONG).show();

        return deliveryaux;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Realidad.this, Home.class);
        startActivity(intent);
    }

}