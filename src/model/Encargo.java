package model;

public class Encargo {
    public String matricula_vehiculo;
    public String fecha_inicio;
    public boolean completado;
    
    
    public Encargo(String matricula_vehiculo){
        this.matricula_vehiculo = matricula_vehiculo;
        // this.fecha_inicio = Citas.getDia(matricula_vehiculo)
        this.completado = false;
    }

    public String getMatricula() { return this.matricula_vehiculo; }       
}
