package model;

public class Asignacion {
    
    public int id;
    //TODO: Subtabla de asignaciones de empleados
    public String id_empleado;
    public String id_encargo;

    public Asignacion(int id, String id_empleado, String id_encargo) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.id_encargo = id_encargo;
    }

    public int getId() { return this.id; }
    public String getEmpleados() { return this.id_empleado; }
    public String getEncargo() { return this.id_encargo; }

}
