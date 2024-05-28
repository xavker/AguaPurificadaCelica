package com.example.aguapurificadacelica.activities.models;



public class modelo_Encuentranos {
    String nombre, dueno, direccion, ubicacion, disponibilidad, imagen,telefono;

    public modelo_Encuentranos(String nombre, String dueno, String direccion, String ubicacion, String disponibilidad, String imagen, String telefono) {
        this.nombre = nombre;
        this.dueno = dueno;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.imagen = imagen;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public modelo_Encuentranos() {

    }
}