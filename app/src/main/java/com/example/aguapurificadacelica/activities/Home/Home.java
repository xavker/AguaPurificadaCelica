package com.example.aguapurificadacelica.activities.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Conocenos.Conocenos;
import com.example.aguapurificadacelica.activities.Contactanos.Contactanos;
import com.example.aguapurificadacelica.activities.MainActivity;
import com.example.aguapurificadacelica.activities.providers.ConfigProvaider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    private Button mButtonLogin;
    private Button mButtonConocenos;
    private Button mButtonContactanos;
    private ConfigProvaider configProvaider;

    private  Boolean deliveryaux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mButtonLogin=findViewById(R.id.btnSolicitar);
        mButtonConocenos=findViewById(R.id.btnConocenos);
        mButtonContactanos=findViewById(R.id.btnContactanos);
        deliveryaux=false;
        configProvaider=new ConfigProvaider();

        final Animation animation1= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        final Animation animation2= AnimationUtils.loadAnimation(this,R.anim.anim_translate2);
        final Animation animation3= AnimationUtils.loadAnimation(this,R.anim.anim_translate3);

        View v=new View(this);
        v.startAnimation(animation1);
        v.startAnimation(animation2);
        v.startAnimation(animation3);
        mButtonLogin.setAnimation(animation1);
        mButtonConocenos.setAnimation(animation2);
        mButtonContactanos.setAnimation(animation3);
        deliveryaux=deliveryOn();
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if(deliveryaux.equals(true)){
                  //  Toast.makeText(Home.this,String.valueOf(deliveryaux),Toast.LENGTH_LONG).show();
                   intent=new Intent(Home.this, MainActivity.class);
                }else{
                   intent=new Intent(Home.this, FueradeServicio.class);

                }

                startActivity(intent);
                finish();
            }
        });
        mButtonConocenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, Conocenos.class);
                startActivity(intent);
            }
        });
        mButtonContactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, Contactanos.class);
                startActivity(intent);
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
        Intent intent = new Intent(Home.this, Home.class);
        startActivity(intent);
    }

}
