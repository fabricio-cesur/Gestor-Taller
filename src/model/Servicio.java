package model;

import java.util.ArrayList;

public class Servicio {
    public int id;
    public String nombre;
    public String descripcion;
    public ArrayList<Item> items;
    public double precio;

    public Servicio(int id, String nombre, String descripcion, ArrayList<Item> items, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.items = items;
        this.precio = precio;
    }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public ArrayList<Item> getItems() { return this.items; }
    public double getPrecio() { return this.precio; }

}
