package model;

public class Cita {
    //Atributos
    private String fecha; 
    private String hora;
    private String matricula; 
     
    //Constructor
    public Cita( String fecha, String hora, String matricula) {
        this.fecha = fecha;
        this.hora = hora;
        this.matricula = matricula;
    }

    //Geters y seters
    public String getFecha() { return this.fecha; }
    public String getHora() { return this.hora; }
    public String getVehiculoMatricula() { return this.matricula ; }
    
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setVehiculoMatricula(String matricula) { this.matricula = matricula ; }

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() {
        return  " Fecha: " + this.getFecha() + ", hora " + this.getHora() 
        + " matricula: " + this.getVehiculoMatricula();
    }
}
