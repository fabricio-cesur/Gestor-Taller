package model;

import java.util.ArrayList;

public class Empleado {
    public String dni;
    public String nombre;
    public String apellido;
    public String direccion;
    public int telefono;
    public String cuenta_bancaria;
    public int salario;
    public String cargo;
    
    
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

    //TODO: Mostrar y toString
    //mostrar() será mostrar todo el empleado con sus datos
    //toString() será para mostrar el empleado resumido en una sóla línea con sus datos más importantes
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
    

    public void add(ArrayList<Empleado> empleado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
