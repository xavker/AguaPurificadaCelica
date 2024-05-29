package com.example.aguapurificadacelica.activities.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;


public class Splash extends AppCompatActivity {
   // SharedPreferences preferecias= PreferenceManager.getDefaultSharedPreferences(Splash.this);
    private AlertDialog politica;
    private AlertDialog.Builder bpolitica;
    String PREFES_KEY="mispreferencias";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean muestra=getValuePreference(getApplicationContext());

        if(muestra){
            saveValupreference(getApplicationContext(),false);

            bpolitica= new AlertDialog.Builder(Splash.this);
            bpolitica.setTitle("Politicas y proteccion de datos");
            bpolitica.setCancelable(false);
            LayoutInflater inflaterpolitica = LayoutInflater.from(Splash.this);
            View regis1politica = inflaterpolitica.inflate(R.layout.activity_politicadedatos, null,false);
            bpolitica.setView(regis1politica);
            bpolitica.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setContentView(R.layout.activity_splash);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(Splash.this, Home.class);
                            startActivity(intent);
                        }
                    },5000);
                }
            });
            bpolitica.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            politica=bpolitica.create();
            politica.show();

        }else {
            setContentView(R.layout.activity_splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this, Home.class);
                    startActivity(intent);
                }
            },5000);
        }
    }
    public void saveValupreference(Context context,Boolean mostrar){
        SharedPreferences settings=context.getSharedPreferences(PREFES_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor1;
        editor1=settings.edit();
        editor1.putBoolean("license",mostrar);
        editor1.commit();
    }
    public Boolean getValuePreference(Context context){
        SharedPreferences preferences1=context.getSharedPreferences(PREFES_KEY,MODE_PRIVATE);
        return preferences1.getBoolean("license",true);
    }
}
