package model;

public class Proveedor {
    public String id;
    public String nombre;
    public String direccion;
    public String cuenta;

    public Proveedor(String nombre, String direccion, String cuenta) {
        // this.id = autoincrement
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
    }

    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public String getCuenta() { return this.cuenta; }

}
