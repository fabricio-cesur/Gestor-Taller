package model;

public class Empleado {
    public String dni;
    public String nombre;
    public String apellido;
    public String direccion;
    public int telefono;
    public int cuenta_bancaria;
    public int salario;
    public Encargo encargo;
    public boolean disponibilidad;
    
    

    public Empleado(String dni, String nombre,String apellido, String direccion, int telefono, int cuenta_bancaria, int salario, Encargo encargo, boolean disponibilidad){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
        this.salario = salario;
        this.encargo = encargo;
        this.disponibilidad = disponibilidad;
    }
}
