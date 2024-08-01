package com.example.aguapurificadacelica.activities.Home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import com.example.aguapurificadacelica.R;


public class Splash extends AppCompatActivity {
   // SharedPreferences preferecias= PreferenceManager.getDefaultSharedPreferences(Splash.this);
    private AlertDialog politica;
    private AlertDialog.Builder bpolitica;
    String PREFES_KEY="mispreferencias";
    private boolean tienePermisoCamara = false;
    private static final int CODIGO_PERMISOS_CAMARA = 1;


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
    private void verificarYPedirPermisosDeCamara() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(Splash.this, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            permisoDeCamaraConcedido();
        } else {
            // Si no, entonces pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(Splash.this,
                    new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoDeCamaraConcedido();
                } else {
                    permisoDeCamaraDenegado();
                }
                break;


            // Aquí más casos dependiendo de los permisos
            // case OTRO_CODIGO_DE_PERMISOS...

        }
    }
    private void permisoDeCamaraConcedido() {
        // Aquí establece las banderas o haz lo que
        // ibas a hacer cuando el acceso a la cámara se condeciera
        // Por ejemplo puedes poner la bandera en true y más
        // tarde en otra función comprobar esa bandera
        Toast.makeText(Splash.this, "El permiso para la cámara está concedido", Toast.LENGTH_SHORT).show();
        tienePermisoCamara = true;
    }

    private void permisoDeCamaraDenegado() {
        // Esto se llama cuando el usuario hace click en "Denegar" o
        // cuando lo denegó anteriormente
        Toast.makeText(Splash.this, "El permiso para la cámara está denegado", Toast.LENGTH_SHORT).show();
    }
}
