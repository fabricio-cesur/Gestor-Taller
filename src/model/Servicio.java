package model;

public class Servicio {
    public String id;
    public String nombre;
    public String descripcion;
    public String item;
    public double precio;
    
    public Servicio(String nombre, String descripcion, String item, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.item = item;
        this.precio = precio;
    }
    
    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public String getItemCodigo() { return this.item; }
    //TODO: método para conseguir el item.
    // public String getItem() { return this.item; }
    public double getPrecio() { return this.precio; }
    
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setItem(String item) { this.item = item; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() { 
        return "ID: " + this.getId() + " " + this.getNombre() 
        + "\n" + this.getDescripcion() 
        + "\nItem: " + this.getItemCodigo() + "Precio: " + this.getPrecio() ;
    }
}
