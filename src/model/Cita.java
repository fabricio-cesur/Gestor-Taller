package model;

public class Cita {
    public int id;
    public String fecha; //Hacerlo Arrays de int?
    public String hora;
    public Vehiculo vehiculo;
    public Encargo encargo;

    public Cita(int id, String fecha, String hora, Vehiculo vehiculo, Encargo encargo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.vehiculo = vehiculo;
        this.encargo = encargo;
    }

    public int getId() { return this.id; }
    public String getFecha() { return this.fecha; }
    public String getHora() { return this.hora; }
    public Vehiculo getVehiculo() { return this.vehiculo; }
    public String getVehiculoMarcaModelo() { return this.getVehiculo().getMarca() + " " + this.getVehiculo().getModelo(); }
    public Encargo getEncargo() { return this.encargo; }

    public void setId(int id) { this.id = id; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    public void setEncargo(Encargo encargo) { this.encargo = encargo; }

    @Override
    public String toString() {
        return this.getId() + ": " 
        + this.getFecha() + " " + this.getHora() 
        + " vehiculo: " + this.getVehiculoMarcaModelo() 
        + " servicio: " + this.getEncargo().getServicio();
    }
}
