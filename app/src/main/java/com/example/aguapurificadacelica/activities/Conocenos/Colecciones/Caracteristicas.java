package com.example.aguapurificadacelica.activities.Conocenos.Colecciones;

public class Caracteristicas {
    private String nombre;
    private String descripcion;
    private String imagen;
    private int image;

    public Caracteristicas() {

    }

    public Caracteristicas(int image,String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.image = image;
    }

    public Caracteristicas(String imagen, String nombre, String descripcion ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
