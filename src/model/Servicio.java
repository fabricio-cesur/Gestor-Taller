package model;

import java.util.ArrayList;

public class Servicio {
    public String id;
    public String nombre;
    public String descripcion;
    public ArrayList<String> items;
    public double precio;

    public Servicio(String id, String nombre, String descripcion, ArrayList<String> items, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.items = items;
        this.precio = precio;
    }

    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public ArrayList<String> getItems() { return this.items; }
    public double getPrecio() { return this.precio; }

}
