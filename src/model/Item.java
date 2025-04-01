package model;

public class Item {
    public String codigo;
    public String nombre;
    public String id_proveedor;
    public String minimo;
    public String precio;
    public String cantidad;
    public boolean restock;

    public Item(String codigo, String nombre, String id_proveedor, String minimo, String cantidad, String precio) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.precio = precio;
        this.minimo = minimo;
        this.cantidad = cantidad;
        this.restock = restock;
    }

    
    public String getCodigo() { return this.codigo; }
    public String getNombre() { return this.nombre; }
    public String getIdProveedor() { return this.id_proveedor; }
    public String getPrecio() { return this.precio; }
    public String getMinimo() { return this.minimo; }
    public String getCantidad() { return this.cantidad; }
    public boolean getRestock() { return this.restock; }



    @Override
    public String toString() { 
        return "Codigo: " + this.getCodigo() + ", Nombre: " + this.getNombre() + ", Id del proveedor: " + this.getIdProveedor() 
        + ", precio: " + this.getPrecio() + ", cantidad mínima: " + this.getMinimo()
        + ", cantidad: " + this.getCantidad();
    }
}
