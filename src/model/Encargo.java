package model;

public class Encargo {
    public int id;
    public Vehiculo vehiculo;
    public Servicio servicio;
    public Double precio_total;
    public Asignacion asignacion;
    public String fecha_inicio;
    public String fecha_finalizado;
    public boolean completado;
    public Item item;

    public Encargo(int id, Vehiculo vehiculo, Servicio servicio, Double precio_total, Asignacion asignacion, String fecha_inicio, String fecha_finalizado, boolean completado, Item item){
        this.id = id;
        this.vehiculo = vehiculo;
        this.servicio = servicio;
        this.precio_total = precio_total;
        this.asignacion = asignacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizado = fecha_finalizado;
        this.completado = completado;
        this.item = item;
    }

    public int getId() { return this.id; }
    public Vehiculo getVehiculo() { return this.vehiculo; }
    public Servicio getServicio() { return this.servicio; }
    public Double getPrecio_total() { return this.precio_total; }
    public Asignacion getAsignacion() { return this.asignacion; }
    public String getFechaInicio() { return this.fecha_inicio; }
    public String getFechaFinalizado() { return this.fecha_finalizado; }
    public boolean getCompletado() { return this.completado; }
    public Item getItem() { return this.item; }
}
