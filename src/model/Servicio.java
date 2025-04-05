package model;

public class Servicio {
    //Atributos
    private int id;
    private String nombre;
    private String descripcion;
    private int id_item;
    private double precio;
    
    //Constructor
    public Servicio(String nombre, String descripcion, int id_item, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_item = id_item;
        this.precio = precio;
    }
    
    //Geter y seter
    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public int getItemCodigo() { return this.id_item; }
    public double getPrecio() { return this.precio; }
    
    
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setItem(int id_item) { this.id_item = id_item; }
    public void setPrecio(double precio) { this.precio = precio; }

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() { 
        return "ID: " + getId() + ", nombre: " + this.getNombre() 
        + ", descripción: " + this.getDescripcion() 
        + ", código del item que se utiliza: " + this.getItemCodigo() + ", precio: " + this.getPrecio() ;
    }
}
