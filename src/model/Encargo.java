package model;

import java.time.LocalDate;

public class Encargo {
    public int id;
    public String matricula_vehiculo;
    public Double precio_total;
    public LocalDate fecha_inicio;
    public LocalDate fecha_finalizado;
    public boolean completado;
    
    
    public Encargo(String matricula_vehiculo){
        this.matricula_vehiculo = matricula_vehiculo;
        this.completado = false;
    }

    public int getId() { return this.id; } 
    public String getMatricula() { return this.matricula_vehiculo; } 
    public Double getPrecioTotal() { return this.precio_total; }
    public LocalDate getFechaInicio() { return this.fecha_inicio; }
    public LocalDate getFechaFinalizado() { return this.fecha_finalizado; }
    public boolean getCompletado() { return this.completado; }
    
    public void setId(int id) { this.id = id; } 
    public void setMatricula(String matricula_vehiculo) { this.matricula_vehiculo = matricula_vehiculo; } 
    public void setPrecioTotal(Double precio_total) { this.precio_total = precio_total; }
    public void setFechaInicio(LocalDate fecha_inicio) { this.fecha_inicio = fecha_inicio; }
    public void setFechaFinalizado(LocalDate fecha_finalizado) { this.fecha_finalizado = fecha_finalizado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " Matrícula: " + this.getMatricula() 
        + "Precio total: " + this.getPrecioTotal() 
        + "Inicio: " + this.getFechaInicio() + " Finalizado: " + this.getFechaInicio() 
        + "Completado: " + this.getCompletado();
    }
}
