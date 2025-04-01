package model;

public class Pedido {

    public String id;
    public String cod_item;
    public String cantidad;
    public String id_proveedor;
    public String fecha_pedido;
    public String fecha_entrega_aproximada;
    public String fecha_recivido;
    public boolean completado;

    public Pedido(String id, String cod_item, String cantidad, String id_proveedor, String fecha_pedido, String fecha_entrega_aproximada, String fecha_recivido, boolean completado){

        this.id = id;
        this.cod_item = cod_item;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.fecha_pedido = fecha_pedido; 
        this.fecha_entrega_aproximada = fecha_entrega_aproximada;
        this.fecha_recivido = fecha_recivido;
        this.completado = completado;
    }
    
}
