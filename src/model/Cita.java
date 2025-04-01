package model;

public class Cita {
    
    public String fecha; 
    public String hora;
    public String matricula; 
     

    public Cita( String fecha, String hora, String matricula) {
        this.fecha = fecha;
        this.hora = hora;
        this.matricula = matricula;
    }

    public String getFecha() { return this.fecha; }
    public String getHora() { return this.hora; }
    public String getVehiculoMatricula() { return this.matricula ; }
    
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setVehiculoMatricula(String matricula) { this.matricula = matricula ; }

    @Override
    public String toString() {
        return  " Fecha: " + this.getFecha() + ", hora " + this.getHora() 
        + " matricula: " + this.getVehiculoMatricula();
    }
}
