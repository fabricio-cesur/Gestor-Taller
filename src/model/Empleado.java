package model;

public class Empleado {
    public String dni;
    public String nombre;
    public String apellido;
    public String direccion;
    public int telefono;
    public String cuenta_bancaria;
    public int salario;
    public String cargo;
    public Encargo encargo;
    public boolean disponibilidad;
    
    public Empleado(String dni, String nombre,String apellido, String direccion, int telefono, String cuenta_bancaria, int salario, String cargo){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
        this.salario = salario;
        this.cargo = cargo;
    }

    //TODO: Getters y Setters

    //TODO: Mostrar y toString
    //mostrar() será mostrar todo el empleado con sus datos
    //toString() será para mostrar el empleado resumido en una sóla línea con sus datos más importantes
}
