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
    public Encargo getEncargo() { return this.encargo; }

}
