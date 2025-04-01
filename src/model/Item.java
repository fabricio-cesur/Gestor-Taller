package model;

public class Item {
    
    public String codigo;
    public String nombre;
    public String id_proveedor;
    public String posicion; // No lo veo Necesario
    public String minimo;
    public boolean restock;

    public Item(String codigo, String nombre, String id_proveedor, String posicion, String minimo, boolean restock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.posicion = posicion;
        this.minimo = minimo;
        this.restock = restock;
    }

    public String getCodigo() { return this.codigo; }
    public String getNombre() { return this.nombre; }
    public String id_getProveedor() { return this.id_proveedor; }
    public String getPosicion() { return this.posicion; }
    public String getMinimo() { return this.minimo; }
    public boolean getRestock() { return this.restock; }

}
