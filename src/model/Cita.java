package model;

public class Cita {
    public String id;
    public String fecha; //Hacerlo Arrays de int?
    public String hora;
    public String matricula_coche; 
    public String id_encargo; 

    //En vez de Encargo encargo en el constructor creo que seria mejor utilizar id_encargo
    public Cita(String id, String fecha, String hora, String matricula_coche, String id_encargo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.matricula_coche = matricula_coche;
        this.id_encargo = id_encargo;
    }

    public String getId() { return this.id; }
    public String getFecha() { return this.fecha; }
    public String getHora() { return this.hora; }
    public String getVehiculoMatricula() { return this.matricula_coche ; }
    public String getIdEncargo() { return this.id_encargo; }

    public void setId(String id) { this.id = id; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setVehiculoMatricula() { this.matricula_coche = matricula_coche ; }
    public void setIdEncargo() { this.id_encargo = id_encargo; }

    @Override
    public String toString() {
        return "Id: " + this.getId() + 
        ", fecha: " + this.getFecha() + ", hora " + this.getHora() 
        + " matricula: " + this.getVehiculoMatricula() 
        + ", id_encargo: " + this.getIdEncargo();
    }
}
