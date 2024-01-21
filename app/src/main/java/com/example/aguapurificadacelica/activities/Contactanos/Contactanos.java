package com.example.aguapurificadacelica.activities.Contactanos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Home.Home;


import de.hdodenhof.circleimageview.CircleImageView;

public class Contactanos extends AppCompatActivity {
    ImageView fb, wh1, wh2, email, call,wh3;
    private CircleImageView mCircleImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        fb = findViewById(R.id.imageViewfp);
        wh1 = findViewById(R.id.imageViewwh1);
        wh2 = findViewById(R.id.imageViewwh2);
        wh3 = findViewById(R.id.imageViewwh3);
        call = findViewById(R.id.imageViewcall);
        email = findViewById(R.id.imageVievemail);
        mCircleImageBack = findViewById(R.id.circleImageBack);

        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Contactanos.this, Home.class);
                startActivity(intent);
                finish();
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Toast.makeText(Contactanos.this, "Redireccionando a facebook", Toast.LENGTH_SHORT).show();
                                      try {
                                          Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/112246227321660"));
                                          startActivity(intent);
                                      } catch (Exception e) {
                                          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/aguacelica")));
                                      }
                                  }
                              });

                wh1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Contactanos.this, "Redireccionando a whatsapp", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.setPackage("com.whatsapp");
                        intent.putExtra(Intent.EXTRA_TEXT, "Buen día, necesito un botellon de agua.");
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex) {
                            Toast.makeText(Contactanos.this, "La aplicación Whastapp no se encuentra instalada en el dispositivo.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                wh2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Contactanos.this, "Redireccionando a whatsapp", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "Buen día, necesito un botellon de agua.");
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex) {
                            Toast.makeText(Contactanos.this, "La aplicación Whastapp no se encuentra instalada en el dispositivo.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        wh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contactanos.this, "Redireccionando a whatsapp", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Buen día, necesito un botellon de agua.");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(Contactanos.this, "La aplicación Whastapp no se encuentra instalada en el dispositivo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Contactanos.this, "Redireccionando a llamadas", Toast.LENGTH_SHORT).show();
                       // Intent i = new Intent(Intent.ACTION_CALL);
                        //i.setData(Uri.parse("tel:123456789"));
                        startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" +" 0979724195")));


            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contactanos.this,"Redireccionando a email",Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","aguacelica@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android APP - ");
                startActivity(Intent.createChooser(emailIntent,  "hola"));
            }
        });
    }
}
