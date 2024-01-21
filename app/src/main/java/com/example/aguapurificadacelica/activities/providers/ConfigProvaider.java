package com.example.aguapurificadacelica.activities.providers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigProvaider {
    DatabaseReference mDatabase;

    public ConfigProvaider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("configuracion").child("General");
    }
    public  DatabaseReference getConfig(String itenGeneral){
        return mDatabase.child(itenGeneral);
    }
}
