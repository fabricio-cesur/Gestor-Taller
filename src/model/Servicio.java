package model;

public class Servicio {
    public String id;
    public String nombre;
    public String descripcion;
    public String item;
    public double precio;

    public Servicio(String id, String nombre, String descripcion, String item, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.item = item;
        this.precio = precio;
    }

    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public String getItem() { return this.item; }
    public double getPrecio() { return this.precio; }

}
