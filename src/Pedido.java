public class Pedido {

    public int id;
    public Item item;
    public int cantidad;
    public Proveedor proveedor;
    public String fecha_pedido;
    public String fecha_entrega_aproximada;
    public String fecha_recivido;
    public boolean completado;

    public Pedido(int id, Item item, int cantidad, Proveedor proveedor, String fecha_pedido, String fecha_entrega_aproximada, String fecha_recivido, boolean completado){

        this.id = id;
        this.item = item;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fecha_pedido = fecha_pedido; 
        this.fecha_entrega_aproximada = fecha_entrega_aproximada;
        this.fecha_recivido = fecha_recivido;
        this.completado = completado;
    }
    
}
