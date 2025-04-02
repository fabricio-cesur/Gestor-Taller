package model;

public class Proveedor {
    
    public String nombre;
    public String direccion;
    public String cuenta;

    public Proveedor(String nombre, String direccion, String cuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
    }

    
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public String getCuentaBancaria() { return this.cuenta; }

    @Override
    public String toString() { 
        return "Nombre: " + this.getNombre() + ", dirección: " + this.getDireccion()
        + ", número de cuenta: " + this.getCuentaBancaria();
    }
}
