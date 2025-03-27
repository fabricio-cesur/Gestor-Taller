package model;


import java.util.ArrayList;

public class Asignacion {
    
    public int id;
    public ArrayList<Empleado> empleados;
    public Encargo encargo;

    public Asignacion(int id, ArrayList<Empleado> empleados, Encargo encargo) {
        this.id = id;
        this.empleados = empleados;
        this.encargo = encargo;
    }

    public int getId() { return this.id; }
    public ArrayList<Empleado> getEmpleados() { return this.empleados; }
    public Encargo getEncargo() { return this.encargo; }

}
