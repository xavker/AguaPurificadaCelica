package com.example.aguapurificadacelica.activities.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Conocenos.Conocenos;
import com.example.aguapurificadacelica.activities.Contactanos.Contactanos;
import com.example.aguapurificadacelica.activities.Encuentranos.Encuentranos;
import com.example.aguapurificadacelica.activities.MainActivity;
import com.example.aguapurificadacelica.activities.RealidadVirtual.Realidad;
import com.example.aguapurificadacelica.activities.models.Datos;
import com.example.aguapurificadacelica.activities.providers.ConfigProvaider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    private Button mButtonLogin;
    private Button mButtonConocenos;
    private Button mButtonContactanos;
    private Button mButtonEncuentranos;
    private Button mButtonpedir1b;
    private Button celica,macara;
    private ConfigProvaider configProvaider;
    private  AlertDialog alert;
    private AlertDialog.Builder builder;
    private  Boolean deliveryaux;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mButtonLogin=findViewById(R.id.btnSolicitar);
        mButtonConocenos=findViewById(R.id.btnConocenos);
        mButtonContactanos=findViewById(R.id.btnContactanos);
        mButtonEncuentranos=findViewById(R.id.btnEncuentranoss);
        mButtonpedir1b=findViewById(R.id.btnpedirunbidon);
        celica=findViewById(R.id.button_celica);
        macara=findViewById(R.id.button_macara);

        deliveryaux=false;
        configProvaider=new ConfigProvaider();

        final Animation animation1= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        final Animation animation2= AnimationUtils.loadAnimation(this,R.anim.anim_translate2);
        final Animation animation3= AnimationUtils.loadAnimation(this,R.anim.anim_translate3);
        final Animation animation4= AnimationUtils.loadAnimation(this,R.anim.anim_translate4);
        final Animation animation5= AnimationUtils.loadAnimation(this,R.anim.anim_translate5);

        View v=new View(this);
        v.startAnimation(animation1);
        v.startAnimation(animation2);
        v.startAnimation(animation3);
        v.startAnimation(animation4);
        v.startAnimation(animation5);
        mButtonpedir1b.setAnimation(animation1);
        mButtonLogin.setAnimation(animation2);
        mButtonConocenos.setAnimation(animation3);
        mButtonContactanos.setAnimation(animation5);
        mButtonEncuentranos.setAnimation(animation4);

        deliveryaux=deliveryOn();

        LayoutInflater inflater = LayoutInflater.from(Home.this);
        View regis1 = inflater.inflate(R.layout.popup_ciudad, null,false);



        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if(deliveryaux.equals(true)){
                  //  Toast.makeText(Home.this,String.valueOf(deliveryaux),Toast.LENGTH_LONG).show();
                   intent=new Intent(Home.this, MainActivity.class);
                }else{
                   intent=new Intent(Home.this, Realidad.class);

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

        mButtonpedir1b.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Datos cliente = new Datos("idPedido", true, 10.0, "1b", "0979724195", "macara", "7/05/2024", "1,098", "1.0254", "xavier ramon", "nota", "1", "javier");
                                                  builder= new AlertDialog.Builder(Home.this);

                                                  builder.setTitle("Ciudad del pedido");
                                                  builder.setCancelable(false);
                                                  builder.setMessage("Selecciona la ciudad donde te encuentras");

                                                  celica=new Button(Home.this);
                                                  macara=new Button(Home.this);
                                                  celica=regis1.findViewById(R.id.button_celica);
                                                  macara=regis1.findViewById(R.id.button_macara);


                                                  if (regis1.getParent() != null)
                                                      ((ViewGroup) regis1.getParent()).removeView(regis1);
                                                  builder.setView(regis1);


                                                  celica.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          Toast.makeText(Home.this,"Envio a Celica",Toast.LENGTH_SHORT).show();
                                                          DialogodePedido dialogo=new DialogodePedido(Home.this);
                                                          cliente.setCelular("593982655500");
                                                          dialogo.pedido_popup(cliente);


                                                          }
                                                  });
                                                  macara.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          Toast.makeText(Home.this,"Envio a Macará",Toast.LENGTH_SHORT).show();
                                                          DialogodePedido dialogo=new DialogodePedido(Home.this);
                                                          cliente.setCelular("593985484953");
                                                          dialogo.pedido_popup(cliente);

                                                      }
                                                  });
                                                  builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                         // dialog.cancel();
                                                          dialog.dismiss();

                                                          //alert=null;
                                                          //builder=null;
                                                      }

                                                  });

                                                  alert=builder.create();
                                                  alert.show();

                                              }
                                          }
        );


        mButtonEncuentranos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, Encuentranos.class);
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
