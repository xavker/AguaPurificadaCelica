package com.example.aguapurificadacelica.activities.Encuentranos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.includes.MyToolbar;
import com.example.aguapurificadacelica.activities.models.modelo_Encuentranos;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Encuentranos extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private Marker marcador;
    private static final float camera_zoom = 15;
    ImageView imgmarker;
    private BottomSheetBehavior mBottomSheetBehavior1;
    LinearLayout tapactionlayout;
    View white_forground_view;
    View bottomSheet;
    TextView txtnombre_local, txtDireccion, txtdescripcion,nombre;

    private ArrayList<modelo_Encuentranos> listaPuntos;
    //private modelo_Encuentranos modelo;

    DatabaseReference mdatabase;
    private modelo_Encuentranos modelo_encuentranos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuentranos);
        listaPuntos=new ArrayList<>();
        MyToolbar.show(this, "Encuentranos", true);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mdatabase= FirebaseDatabase.getInstance().getReference("encuentranos1X3gIT0enkkRXEY-qptnwptoagJzI5t6EI1BgpCK2zx0");
        View headerLayout1 = findViewById(R.id.bottomJsoft);
        imgmarker = (ImageView)findViewById(R.id.ImgMarker);
        nombre=findViewById(R.id.nombre);
        txtnombre_local = (TextView)findViewById(R.id.txtNombreLocal);
        txtDireccion = (TextView)findViewById(R.id.txtDireccion);
        txtdescripcion = (TextView)findViewById(R.id.txtDescripcion);
        tapactionlayout =(LinearLayout)findViewById(R.id.tap_action_layout);
        bottomSheet = findViewById(R.id.bottomJsoft);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior1.setPeekHeight(120);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior1.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    tapactionlayout.setVisibility(View.VISIBLE);
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    tapactionlayout.setVisibility(View.GONE);
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    tapactionlayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        tapactionlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBottomSheetBehavior1.getState()==BottomSheetBehavior.STATE_COLLAPSED)
                {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng celica = new LatLng(-4.10244387747826, -79.95613047375245);
        map.addMarker(new MarkerOptions().position(celica).title("Planta Agua Celica").icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_botella_de_agua_64)));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(celica).zoom(23).build();
        map.moveCamera(CameraUpdateFactory.newLatLng(celica));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        cargarDatos();
        //CargarPuntosAMapa(lista2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Eliminar_Iconos) {
            Toast.makeText(getApplicationContext(), "Eliminando iconos", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        try{
            Integer clickCount = (Integer) marker.getTag();
// return true;
        }
        catch (Exception ex) {
            modelo_Encuentranos info = new modelo_Encuentranos();
            info = (modelo_Encuentranos) marker.getTag();
            String url__img = info.getImagen();
            Glide.with(this)
                    .load(url__img)//Config.URL_IMAGES_PUBLIC + url__img+".jpg")
                    .crossFade()
                    .centerCrop()
                    .placeholder(R.drawable.letras)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imgmarker);
            txtnombre_local.setText(info.getDueno());
            txtDireccion.setText(info.getDireccion());
            txtdescripcion.setText(Segmentar_descripcion(info.getDisponibilidad()));
            nombre.setText(info.getNombre());
            //, txtDireccion, txtHorario
            mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        return false;
    }
    private void cargarDatos() {
        mdatabase.child("celica").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaPuntos.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        modelo_Encuentranos modelo = dataSnapshot1.getValue(modelo_Encuentranos.class);
                        listaPuntos.add(modelo);
                    }
                    ArrayList<modelo_Encuentranos> lista2=listaPuntos;
                    Log.i("NOMBRES", Integer.toString(lista2.size()));
                    CargarPuntosAMapa(lista2);
                    //iFirebaseLoadDone.onFirebaseloadSuccesss(models);
                } else {
                    Toast.makeText(Encuentranos.this, "NO EXTISTE ESTE DATOS", Toast.LENGTH_LONG).show();
                }
               // listaPuntos=new ArrayList<>();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
        //Log.i("NOMBRES", String.valueOf(listaPuntos.size()));


    }
    @TargetApi(Build.VERSION_CODES.N)
    private void CargarPuntosAMapa(ArrayList<modelo_Encuentranos> lista2) {
        //map.clear();
        Log.i("NOMBRES", String.valueOf(listaPuntos.size()));
        if (lista2.size() > 0) {
            LatLng ultpos = null;
            for (int i = 0; i < lista2.size(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                Double lat=Double.parseDouble(Segmentar_ubicacion(lista2.get(i).getUbicacion(),0));
                Double lng=Double.parseDouble(Segmentar_ubicacion(lista2.get(i).getUbicacion(),1));

                markerOptions.position(new LatLng(lat,lng))
                             .title(lista2.get(i).getNombre())
                             .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_ubicaci_n_de_la_tienda_100));
                Marker marcador_ = map.addMarker(new MarkerOptions()
                                      .position(new LatLng(lat,lng))
                                      .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_ubicaci_n_de_la_tienda_100))
                                     .title(lista2.get(i).getNombre()));
                //Log.i("ubicacion",lista2.get(1).getNombre());
                marcador_.setTag(lista2.get(i));
                // marcador.setTag(i);
                ultpos = new LatLng(lat,lng);
            }
            VolverPosicion(ultpos);
        }
        else {
            Toast.makeText(this, "Lo sentimos, no tenemos agentes en la Unidad Seleccionada", Toast.LENGTH_SHORT).show();
        }
        map.setOnMarkerClickListener(this);
    }

    private String Segmentar_ubicacion(String ubicacion, int i) {

        String[] parts = ubicacion.split(",");
        String part1 = parts[0]; // 123
        String part2 = parts[1]; // 654321
        String resultado="";
        if(i==0){
            resultado=part1;
        }else{
            resultado=part2;

        }
        return  resultado;
    }
    private String Segmentar_descripcion(String texto) {
        String descripcion="Ofrece productos de primera necesidad, ademas podras encontrar:";
        String[] parts = texto.split(",");
        for(int i=0;i<parts.length;i++){
            if(parts[i].equals("b")){
                descripcion+=" botellones normales, ";
            } else if (parts[i].equals("bll")) {
                descripcion+=" botellones de llave, ";
            }else if (parts[i].equals("p600")) {
                descripcion+=" pacas de botellas de 600 ml, ";
            }else if (parts[i].equals("p1000")) {
                descripcion+=" pacas de botellas de 1000 ml, ";
            }else if (parts[i].equals("g")) {
                descripcion+=" galones de 5 litros, ";
            }else if (parts[i].equals("g10")) {
                descripcion+=" pacas de 10 litros, ";
            }
        }
        descripcion+=" de agua purificada celica.";
        return descripcion;
    }


    private void VolverPosicion(LatLng miLatLng) {
// mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydne
        CameraPosition camPos = new CameraPosition.Builder()
                .target(miLatLng) //Centramos el mapa en Madrid
                .zoom(camera_zoom) //Establecemos el zoom en 19
                .bearing(45) //Establecemos la orientación con el noreste arriba
                .tilt(70) //Bajamos el punto de vista de la cámara 70 grados
                .build();
        CameraUpdate miUbicacion = CameraUpdateFactory.newCameraPosition(camPos);
        map.animateCamera(miUbicacion);
    }
}