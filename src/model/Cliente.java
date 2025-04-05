package model;

public class Cliente {
    //Atributos
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private String cuenta_bancaria;
    
    //Constructor
    public Cliente(String dni, String nombre, String apellido, String direccion, int telefono, String cuenta_bancaria){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
    }

    //Geter y seter
    public String getDni() { return this.dni; }
    public String getNombre() { return this.nombre; }
    public String getApellido() { return this.apellido; }
    public String getDireccion() { return this.direccion; }
    public int getTelefono() { return this.telefono; }
    public String getCuentaBancaria() { return this.cuenta_bancaria; }

    public void setDni(String dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public void setCuentaBancaria(String cuenta_bancaria) { this.cuenta_bancaria = cuenta_bancaria; }

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() { 
        return "DNI: " + this.getDni() + ", Nombre: " + this.getNombre() + ", Apellidos: " + this.getApellido() 
        + ", telefono: " + this.getTelefono() + ", dirección: " + this.getDireccion()
        + ", número de cuenta: " + this.getCuentaBancaria();
    }
}
