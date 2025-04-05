package model;

public class Proveedor {
    
    //Atributos
    private String nombre;
    private String direccion;
    private String cuenta;

    //Constructor
    public Proveedor(String nombre, String direccion, String cuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
    }

    //Geter y seter
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public String getCuentaBancaria() { return this.cuenta; }

    public void setNombre(String nombre) {  this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCuentaBancaria(String cuenta) {  this.cuenta = cuenta;}   
        
    //MEtodo que se utiliza a imprimir el objeto

    @Override
    public String toString() { 
        return "Nombre: " + this.getNombre() + ", dirección: " + this.getDireccion()
        + ", número de cuenta: " + this.getCuentaBancaria();
    }
}
