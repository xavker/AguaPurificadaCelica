package com.example.aguapurificadacelica.activities.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;


public class FueradeServicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerade_servicio);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FueradeServicio.this,Home.class);
        startActivity(intent);
        finish();


    }
}
