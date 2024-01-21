package com.example.aguapurificadacelica.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Home.Home;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Button mButtonIAmClient;
    Button mButtonIAmDriver;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();


        mButtonIAmClient = findViewById(R.id.btnIAmClient);
        mButtonIAmDriver = findViewById(R.id.btnIAmDriver);

        mButtonIAmClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "client");
                editor.apply();
                goToSelectAuth();
            }
        });
        mButtonIAmDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "driver");
                editor.apply();

                goToValidation();
            }
        });

    }




    private void goToValidation() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_cuadrodedialogo,null);
        builder.setView(view);

        final AlertDialog dialog=builder.create();
        dialog.show();

        Button aceptar=view.findViewById(R.id.aceptar);
        Button cancelar=view.findViewById(R.id.cancelar);
        final EditText password=view.findViewById(R.id.clave);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("1721485173")){
                    goToSelectAuth();
                }else{
                    Toast.makeText(MainActivity.this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String user = mPref.getString("user", "");
            if (user.equals("client")) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(MainActivity.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }
}
