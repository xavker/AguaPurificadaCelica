package com.example.aguapurificadacelica.activities.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.models.Datos;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogodePedido {

    Context context;

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;

    float valorbidones, valorpacas600, valorpacas1000, valorgalones, valorgalones10;
    Button buttonbllmas, bttonbllmeno, botonaceptar, botoncancelar, buttonbmas, bttonbmeno, button600mas, button600menos, button1000mas, button1000menos, buttongalonmas, buttongalonmenos, buttongalones10mas, buttongalones10menos;
    TextView botellonesll, botellones, b600ml, b1000ml, galones, total, galones10;
    EditText nombre,direccion,nota;
    Float valorb0, valorb1;
    Float p0, p1;
    Float p2;
    Float p3;
    Float p4;
    Float p5;
    String t="";
    String vendidos, pedidos, b0, b1, b2, b3, b4, b5,t1="";
    DecimalFormat f;
    PedidosyVendidos pedidosyVendidos;
    private AlertDialog.Builder dialog;
    private AlertDialog dialog0;
    AlertDialog show;

    public DialogodePedido(Context context) {

        this.context = context;
        valorbidones = 1.5F;
        valorpacas600 = 4.8F;
        valorpacas1000 = 6.00F;
        valorgalones = 0.9F;
        valorgalones10 = 1.25F;
        valorb1 = 1.5f;
        valorb0 = 1.5f;
        vendidos = "0";
        pedidos = "0";

        dialog = new AlertDialog.Builder(context);
        dialog0 = new AlertDialog.Builder(context).create();
        f = new DecimalFormat("##.00");

        preferencias= context.getSharedPreferences("datos",Context.MODE_PRIVATE);
        editor=preferencias.edit();

    }



    public void pedido_popup(Datos datos) {

        dialog.setTitle("Pantalla de pedido");
        dialog.setMessage("Los datos se guardaran para proximos pedidos, puede editarlos cuando usted guste.");
        LayoutInflater inflater = LayoutInflater.from(context);
        View regis = inflater.inflate(R.layout.agregarclientepedido, null);
        pedidosyVendidos = new PedidosyVendidos();
        nombre = regis.findViewById(R.id.nombrea);
        direccion=regis.findViewById(R.id.direcciona);
        nota = regis.findViewById(R.id.notaa);

        buttonbllmas = regis.findViewById(R.id.buttonBllmas);
        bttonbllmeno = regis.findViewById(R.id.buttonBllmenos);
        buttonbmas = regis.findViewById(R.id.buttonBmas);
        bttonbmeno = regis.findViewById(R.id.buttonBmenos);
        button600mas = regis.findViewById(R.id.button600mlmas);
        button600menos = regis.findViewById(R.id.button600mlmenos);
        button1000mas = regis.findViewById(R.id.button1000mlmas);
        button1000menos = regis.findViewById(R.id.button1000mlmenos);
        buttongalonmas = regis.findViewById(R.id.button5lmas);
        buttongalonmenos = regis.findViewById(R.id.buttongalonesmenos);
        buttongalones10mas = regis.findViewById(R.id.button10lmas);
        buttongalones10menos = regis.findViewById(R.id.buttongalones10menos);

        botellonesll = regis.findViewById(R.id.botellonesll);
        botellones = regis.findViewById(R.id.botellones);
        b600ml = regis.findViewById(R.id.pacas600);
        b1000ml = regis.findViewById(R.id.pacas1000);
        galones = regis.findViewById(R.id.galones);
        galones10 = regis.findViewById(R.id.galones10);

        total = regis.findViewById(R.id.totalpedido);

        botonaceptar = regis.findViewById(R.id.baceptar);
        botoncancelar = regis.findViewById(R.id.bcancelar);



        if (datos != null) {
            cargar_datos();
            //nombre.setText(datos.getNombre());
            // spinner_barrios.set;
            //String[] aux2 = ordenarbarrio(datos.getDireccion());
        }

        buttonbllmas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String aux = botellonesll.getText().toString();
                botellonesll.setText(String.valueOf(Integer.parseInt(aux) + 1));
                //valorb1=Float.parseFloat(spinnerll.getSelectedItem().toString());
                valortotal();
            }
        });
        bttonbllmeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(botellonesll.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                //valorb1=Float.parseFloat(spinnerll.getSelectedItem().toString());
                botellonesll.setText(String.valueOf(aux - 1));
                valortotal();


            }
        });
        buttonbmas.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String aux = botellones.getText().toString();
                botellones.setText(String.valueOf(Integer.parseInt(aux) + 1));
                //valorb0=Float.parseFloat(spinner.getSelectedItem().toString());
                valortotal();
            }
        });
        bttonbmeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(botellones.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                //valorb0=Float.parseFloat(spinner.getSelectedItem().toString());
                botellones.setText(String.valueOf(aux - 1));
                valortotal();


            }
        });

        button600mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = b600ml.getText().toString();
                b600ml.setText(String.valueOf(Integer.parseInt(aux) + 1));
                // valorpacas600=Float.parseFloat(spinnerp600.getSelectedItem().toString());
                valortotal();


            }
        });
        button600menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(b600ml.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                b600ml.setText(String.valueOf(aux - 1));
                 valortotal();


            }
        });
        button1000mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = b1000ml.getText().toString();
                b1000ml.setText(String.valueOf(Integer.parseInt(aux) + 1));
                valortotal();


            }
        });
        button1000menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(b1000ml.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                b1000ml.setText(String.valueOf(aux - 1));
                valortotal();


            }
        });

        buttongalonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = galones.getText().toString();
                galones.setText(String.valueOf(Integer.parseInt(aux) + 1));
                valortotal();


            }
        });
        buttongalonmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(galones.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                galones.setText(String.valueOf(aux - 1));
                valortotal();
            }
        });

        buttongalones10mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = galones10.getText().toString();
                galones10.setText(String.valueOf(Integer.parseInt(aux) + 1));
                valortotal();
            }
        });
        buttongalones10menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux = Integer.parseInt(galones10.getText().toString());
                if (aux <= 0) {
                    aux = 1;
                }
                galones10.setText(String.valueOf(aux - 1));
                valortotal();

            }
        });
        dialog.setView(regis);
        show = dialog.show();

        botonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nombre.getText().toString())) {
                    Toast.makeText(context, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
                    nombre.requestFocus();
                    nombre.setHint("!!!Falta nombre!!!");


                } else if (TextUtils.equals(botellones.getText().toString(), "0")
                        && TextUtils.equals(botellonesll.getText().toString(), "0")
                        && TextUtils.equals(b600ml.getText().toString(), "0")
                        && TextUtils.equals(b1000ml.getText().toString(), "0")
                        && TextUtils.equals(galones.getText().toString(), "0")
                        && TextUtils.equals(galones10.getText().toString(), "0")) {
                    Toast.makeText(context, "Por ingrese algun valor en los pedidos.", Toast.LENGTH_SHORT).show();
                } else {
                    datos.setNombre(nombre.getText().toString());
                    datos.setDireccion(direccion.getText().toString());
                    datos.setNota(nota.getText().toString());

                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss  dd-MM-yyyy ");
                    String formattedDate = df.format(c.getTime());

                    /*VALOR RESUMEN ENVIADO A LA SIGUIENTE ACTIVIDTY
                    b0= n de botellones
                    p0=valor botellon
                    b1= numero de botellone de llave
                     p1=valor de botellon de llave
                    b2= numero de botellones de 600
                     p2=valor de 600
                    b3= numero de botellones de 1000
                     p3=valor botellon de litro
                    b4= numero de botellones de 5000
                    p4=valor  de galon
                    b5= numero de botellones de 1000
                    p5=valor botellon de galon 10 l
                    t=total


                    f = new DecimalFormat("##.00");
                    pedidos = pedidosyVendidos.pedidos(
                            b0, f.format(p0),
                            b1, f.format(p1),
                            b2, f.format(p2),
                            b3, f.format(p3),
                            b4, f.format(p4),
                            b5, f.format(p5),
                            t);
*/
                    datos.setPedidos(t);
                    guardar_preferencias();
                    enviar_whatsapp_pedido(datos);
                    show.dismiss();

                }
            }
        });
        botoncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }
        });


    }



    private void enviar_whatsapp_pedido(Datos provedor) {
                try {
                    String trimToNumner = provedor.getCelular(); //10 digit number
                    Intent intent = new Intent ( Intent.ACTION_VIEW );
                    intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner +
                            "/?text=" + "!!!hola buen dia!!! \n Soy "+provedor.getNombre()+"\n"+
                            "Ayudeme con: \n "+provedor.getPedidos()+
                            "a la mi dirección: "+ provedor.getDireccion()+"\n"+
                            provedor.getNota()) );
                    context.startActivity ( intent );
                } catch (Exception e) {
                    e.printStackTrace ();
                }
    }
    private void cargar_datos() {
        nombre.setText(preferencias.getString("nombre","no existe"));
        direccion.setText(preferencias.getString("direccion","no existe"));
        nota.setText(preferencias.getString("nota","no existe"));
    }
    private void guardar_preferencias(){
        editor.putString("nombre",nombre.getText().toString());
        editor.putString("direccion",direccion.getText().toString());
        editor.putString("nota",nota.getText().toString());
        editor.commit();
    }

    private void valortotal() {

        b0 = botellones.getText().toString();
        b1 = botellonesll.getText().toString();
        b2 = b600ml.getText().toString();
        b3 = b1000ml.getText().toString();
        b4 = galones.getText().toString();
        b5 = galones10.getText().toString();

        //p0=Float.parseFloat(b0) * valorb0;
        //p1=Float.parseFloat(b1) * valorb1;
        //p2=Float.parseFloat(b2) * valorpacas600 ;
        //p3= Float.parseFloat(b3) * valorpacas1000;
        //p4=Float.parseFloat(b4) * valorgalones ;
        //p5=Float.parseFloat(b5) * valorgalones10 ;
        t="";
        if (!b0.equals("0")) {
            t = t+ "- "+b0 + "botellon(es) \n";
        }
        if (!b1.equals("0")) {
            t =  t+"- "+ b1 + "botellon(es) de llave \n";
        }  if (!b2.equals("0")) {
            t =   t+"- "+ b2 + "pacas de 600ml \n";
        }  if (!b3.equals("0")) {
            t = t+"- "+  b3 + "paca(s) de 1000ml \n";
        }if (!b4.equals("0")) {
            t =   t+"- "+b4 + "galon(es) de 5l \n";
        }if (!b5.equals("0")) {
            t =  t+"- "+b5 + "galon(es) de 10l.";
            //t=f.format(p0+p1+p2+p3+p4+p5);
        }
        t1=t1+ t;
        total.setText(t);

    }
}



