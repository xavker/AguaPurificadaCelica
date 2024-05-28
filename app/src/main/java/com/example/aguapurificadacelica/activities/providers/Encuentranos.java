package com.example.aguapurificadacelica.activities.providers;

import com.example.aguapurificadacelica.activities.Conocenos.Colecciones.Caracteristicas;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Encuentranos {
    DatabaseReference mDatabase;

    public Encuentranos() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("https://celicaconectedfirebase.firebaseio.com/encuentranos1X3gIT0enkkRXEY-qptnwptoagJzI5t6EI1BgpCK2zx0").child("encuentranos");

    }
    public  DatabaseReference getLugar(String nombre){
        return mDatabase.child(nombre);
    }

    void onFirebaseloadSuccesss(List<Caracteristicas> lugaresList) {

    }

    void onFirebaseLoadFailed(String mensage) {

    }
}
