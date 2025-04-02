package model;

public class Encargo {
    public String matricula_vehiculo;
    public Double precio_total;
    public String fecha_inicio;
    public String fecha_finalizado;
    public boolean completado;
    
    
    public Encargo(String matricula_vehiculo, Double precio_total){
        this.matricula_vehiculo = matricula_vehiculo;
        this.precio_total = precio_total;
        this.completado = false;
    }

    public String getMatricula() { return this.matricula_vehiculo; } 
    public Double getPrecioTotal() { return this.precio_total; }
    public String getFechaInicio() { return this.fecha_inicio; }
    public String getFechaFinalizado() { return this.fecha_finalizado; }
    public boolean getCompletado() { return this.completado; }

    public void setMatricula(String matricula_vehiculo) { this.matricula_vehiculo = matricula_vehiculo; } 
    public void setPrecioTotal(Double precio_total) { this.precio_total = precio_total; }
    public void setFechaInicio(String fecha_inicio) { this.fecha_inicio = fecha_inicio; }
    public void setFechaFinalizado(String fecha_finalizado) { this.fecha_finalizado = fecha_finalizado; }
    public void setCompletado(boolean completado) { this.completado = completado; } 
}
