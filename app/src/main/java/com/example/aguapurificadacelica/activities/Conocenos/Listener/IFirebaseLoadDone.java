package com.example.aguapurificadacelica.activities.Conocenos.Listener;

import com.example.aguapurificadacelica.activities.Conocenos.Colecciones.Caracteristicas;

import java.util.List;

public interface IFirebaseLoadDone {

    void onFirebaseloadSuccesss(List<Caracteristicas> lugaresList);
    void onFirebaseLoadFailed(String mensage);

}
