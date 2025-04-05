package model;

public class Empleado {
    //Atributos
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private String cuenta_bancaria;
    private int salario;
    private String cargo;
    
    //Constructor
    public Empleado(String dni, String nombre,String apellido, String direccion, int telefono, 
    String cuenta_bancaria, int salario, String cargo){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
        this.salario = salario;
        this.cargo = cargo;

    }

    //get y set
    public String getDni() { return this.dni; }
    public String getNombre() { return this.nombre; }
    public String getApellido() { return this.apellido; }
    public String getDireccion() { return this.direccion; }
    public int getTelefono() { return this.telefono; }
    public String getCuentaBancaria() { return this.cuenta_bancaria; }
    public int getSalario() { return this.salario; }
    public String getCargo() { return this.cargo; }
    

    public void setDni(String dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public void setCuentaBancaria(String cuenta_bancaria) { this.cuenta_bancaria = cuenta_bancaria; }
    public String setSargo() { return this.cargo; }
   
    //MEtodo que se utiliza a imprimir el objeto

    @Override
    public String toString() { 
        return "DNI: " + this.getDni() + ", Nombre: " + this.getNombre() + ", Apellidos: " + this.getApellido() 
        + ", telefono: " + this.getTelefono() + ", dirección: " + this.getDireccion()
        + ", número de cuenta: " + this.getCuentaBancaria() + 
        ", salario: " + this.getSalario() + ", cargo: " + this.getCargo();
    }
}
