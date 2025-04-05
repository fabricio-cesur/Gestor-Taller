package model;

import java.time.LocalDate;

public class Pedido {

    //Atributos
    private int id;
    private String codigo_item; 
    private int cantidad_solicitada;
    private LocalDate fecha_pedido;
    private boolean completado;

    // Constructor
    public Pedido(String codigo_item, int cantidad_solicitada, LocalDate fecha_pedido, boolean completado) {
        this.codigo_item = codigo_item;
        this.cantidad_solicitada = cantidad_solicitada;
        this.fecha_pedido = LocalDate.now();
        this.completado = false;
    }
    //gATER Y SETER
    public int getIdPedido() { return this.id; }
    public String getCodigoItem() { return this.codigo_item; }
    public int getCantidadSolicitada() { return this.cantidad_solicitada; }
    public LocalDate getFechaPedido() { return this.fecha_pedido;  }
    public boolean isCompletado() { return this.completado; }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() {
        return "Código de pedido: " + this.id + ", código Item=" + this.codigo_item + ", cantidad=" + this.cantidad_solicitada +
                this.fecha_pedido + ", completado=" + this.completado ;
    }
    
}
