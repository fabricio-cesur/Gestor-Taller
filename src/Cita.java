public class Cita {
    public int id;
    public String fecha; //Hacerlo Arrays de int?
    public String hora;
    public Vehiculo vehiculo;
    // public Encargo encargo;

    public Cita(int id, String fec, String hor, Vehiculo veh /*, Encargo enc*/) {
        this.id = id;
        this.fecha = fec;
        this.hora = hor;
        this.vehiculo = veh;
        // this.encargo = enc;
    }

    public int getId() { return this.id; }
    public String getFecha() { return this.fecha; } //Hacerlo Arrays de int?
    public String getHora() { return this.hora; }
    public Vehiculo getVehiculo() { return this.vehiculo; }
    // public Encargo getEncargo() { return this.encargo; }

}
