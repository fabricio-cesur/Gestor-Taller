package model;

public class Encargo {
    public String id;
    public String matricula_vehiculo;
    public String id_servicio;
    public Double precio_total;
    public String id_asignacion;
    public String fecha_inicio;
    public String fecha_finalizado;
    public String cod_item;

    public Encargo(String id, String matricula_vehiculo, String id_servicio, Double precio_total, String id_asignacion, String fecha_inicio, String fecha_finalizado, String cod_item){
        this.id = id;
        this.matricula_vehiculo = matricula_vehiculo;
        this.id_servicio = id_servicio;
        this.precio_total = precio_total;
        this.id_asignacion = id_asignacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizado = fecha_finalizado;
        this.cod_item = cod_item;
    }

    public String getId() { return this.id; }
    public String getVehiculo() { return this.matricula_vehiculo; }
    public String getServicio() { return this.id_servicio; }
    public Double getPrecio_total() { return this.precio_total; }
    public String getAsignacion() { return this.id_asignacion; }
    public String getFechaInicio() { return this.fecha_inicio; }
    public String getFechaFinalizado() { return this.fecha_finalizado; }
    public String getString() { return this.cod_item; }
}
